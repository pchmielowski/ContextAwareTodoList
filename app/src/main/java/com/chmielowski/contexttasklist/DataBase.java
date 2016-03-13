package com.chmielowski.contexttasklist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

final public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE = "Tasks";
    private static final String COLUMN_ID = "Id";
    private static final String COLUMN_TASK = "Name";

    public DataBase(Context context) {
        super(context, "tasks.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK + "  TEXT" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException();
    }

    public Cursor getTaskNames() {
        return getReadableDatabase().query(DATABASE,
                new String[]{COLUMN_TASK},
                null, null, null, null, null);
    }
}