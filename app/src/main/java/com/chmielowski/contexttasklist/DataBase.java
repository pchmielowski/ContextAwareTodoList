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
    public Iterable<Task> getTasks() {
        Cursor query = getReadableDatabase().query(
                DATABASE,
                new String[]{COLUMN_TASK},
                null, null, null, null, null
        );
        final ArrayList<Task> tasks = new ArrayList<>();
        while (query.moveToNext()) {
            final int taskNamesColumn = 0;
            tasks.add(new SqlTask(false, query.getString(taskNamesColumn)));
        }
        return new Iterable<Task>() {
            @Override
            public Iterator<Task> iterator() {
                return tasks.iterator();
            }
        };
    }
}
