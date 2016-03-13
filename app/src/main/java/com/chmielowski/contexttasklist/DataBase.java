package com.chmielowski.contexttasklist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Iterator;

final public class DataBase extends SQLiteOpenHelper {

    private static final String COLUMN_TASK_NAME = "Name";
    private static final String DATABASE_NAME = "Tasks";
    private static final String COLUMN_ID_NAME = "Id";

    public DataBase(Context context) {
        super(context, "tasks.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE_NAME + " (" +
                COLUMN_ID_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK_NAME + "  TEXT" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException();
    }

    public Iterable<Task> tasks() {
        String[] columns = {COLUMN_TASK_NAME};
        Cursor cursor = getReadableDatabase().query(DATABASE_NAME, columns,
                null, null, null, null, null);

        final ArrayList<Task> list = new ArrayList<Task>();
        while (cursor.moveToNext()) {
            list.add(new Task(false, cursor.getString(0)));
        }

        return new Iterable<Task>() {
            @Override
            public Iterator<Task> iterator() {
                return list.iterator();
            }
        };
    }
}