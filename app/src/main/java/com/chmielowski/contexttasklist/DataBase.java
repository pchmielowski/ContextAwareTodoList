package com.chmielowski.contexttasklist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Iterator;

public final class DataBase extends SQLiteOpenHelper implements Persistence {

    private static final String DATABASE = "Tasks";
    private static final String COLUMN_TASK = "Name";

    public DataBase(final Context context) {
        super(context, "tasks.db", null, 1);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        throw new UnsupportedOperationException();
//        db.execSQL("CREATE TABLE " + DATABASE + " (" +
//                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COLUMN_TASK + "  TEXT" +
//                ");");
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db,
                          final int oldVersion,
                          final int newVersion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Cursor query(String columnName, String where) {
        return getReadableDatabase().query(
                DATABASE,
                new String[]{columnName},
                where,
                null, null, null, null
        );

    }

    @Override
    public Iterable<Integer> getTaskIdxs() {
        Cursor query = getReadableDatabase().query(
                DATABASE,
                new String[]{"id"},
                null, null, null, null, null
        );
        final ArrayList<Integer> tasks = new ArrayList<>();
        while (query.moveToNext()) {
            final int taskNamesColumn = 0;
            tasks.add(query.getInt(taskNamesColumn));
        }
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return tasks.iterator();
            }
        };
    }

}
