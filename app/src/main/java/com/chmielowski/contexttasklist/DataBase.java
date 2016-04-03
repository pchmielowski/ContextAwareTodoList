package com.chmielowski.contexttasklist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public Cursor query(final String columnName, final String where) {
        return getReadableDatabase().query(
                DATABASE,
                new String[]{columnName},
                where,
                null, null, null, null
        );

    }

    @Override
    public String queryForString(final String columnName, final String where)
            throws Exception {
        Cursor answer = query(columnName, where);
        if (answer.moveToNext()) {
            final int nameColumnIdx = 0;
            return answer.getString(nameColumnIdx);
        }
        throw new Exception("No string.");
    }
}
