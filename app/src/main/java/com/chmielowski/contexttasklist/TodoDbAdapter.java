package com.chmielowski.contexttasklist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoDbAdapter extends SQLiteOpenHelper {

    public TodoDbAdapter(Context context) {
        super(context, "tasks.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Tasks (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getAllTasks() {
        String[] columns = {"Id", "Name"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("Tasks", columns, null, null, null, null, null);
        return cursor;
    }
}