package com.chmielowski.contexttasklist.context;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public final class Beacon {
    private final Map<String, Double> beacons = new HashMap<>();
    private ScanCallback beaconScanCallBack = new ScanCallback() {
        private String bytesToHex(byte[] bytes) {
            final char[] hexArray = "0123456789ABCDEF".toCharArray();
            char[] hexChars = new char[bytes.length * 2];
            for (int j = 0; j < bytes.length; j++) {
                int v = bytes[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }
            return new String(hexChars);
        }


        @Override
        public void onScanResult(final int callbackType, final ScanResult message) {
            if (message.getScanRecord() == null) {
                return;
            }
            byte[] scanRecord = message.getScanRecord().getBytes();
            if (!isBeacon(scanRecord)) {
                return;
            }
            // TODO UUID
//            beacons.put(String.valueOf(major(scanRecord)) + String.valueOf(minor(scanRecord)), distance(message));
//            Double minDistance = Double.MAX_VALUE;
//            String closestBeacon = "";
//            for (Map.Entry<String, Double> entry : beacons.entrySet()) {
//                if (entry.getValue() < minDistance) {
//                    minDistance = entry.getValue();
//                    closestBeacon = entry.getKey();
//                }
//            }
//            Log.i("CLOSEST", closestBeacon);
            Log.i("beacon",
//                    uuid(scanRecord) + ", "
//                            + major(scanRecord) + ", "
                    minor(scanRecord) + "    "
                            + distance(message));
        }

        private double distance(ScanResult result) {
            final double txPowerLevel = result.getScanRecord().getTxPowerLevel();
            final double rssi = result.getRssi();
            return -rssi;
        }

        private int minor(byte[] scanRecord) {
            return (scanRecord[27] & 0xff) * 0x100
                    + (scanRecord[28] & 0xff);
        }

        private int major(byte[] scanRecord) {
            return (scanRecord[25] & 0xff) * 0x100
                    + (scanRecord[26] & 0xff);
        }

        private String uuid(byte[] scanRecord) {
            byte[] uuidBytes = new byte[16];
            final int UUID_BEGIN = 9;
            System.arraycopy(scanRecord, UUID_BEGIN, uuidBytes, 0, 16);
            return bytesToHex(uuidBytes);
        }

        private boolean isBeacon(byte[] scanRecord) {
            return ((int) scanRecord[7] == 0x02) && ((int) scanRecord[8] == 0x15);
        }
    };

    public Beacon() {
        BluetoothAdapter
                .getDefaultAdapter()
                .getBluetoothLeScanner()
                .startScan(null,
                        new ScanSettings.Builder()
                                .build(),
                        beaconScanCallBack);
    }
}
