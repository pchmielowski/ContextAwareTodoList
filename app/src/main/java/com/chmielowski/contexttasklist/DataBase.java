package com.chmielowski.contexttasklist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;
import java.util.Vector;

public final class DataBase extends SQLiteOpenHelper implements Persistence {

    private static final String DATABASE_NAME = "Tasks";
    private static final int FIRST_COLUMN = 0;

    public DataBase(final Context context) {
        super(context, "tasks.db", null, 1);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        throw new UnsupportedOperationException();
//        db.execSQL("CREATE TABLE " + DATABASE_NAME + " (" +
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

    private Cursor answerForQuery(final String columnName, final String where) {
        return getReadableDatabase().query(
                DATABASE_NAME,
                new String[]{columnName},
                where,
                null, null, null, null
        );
    }

    @Override
    public String string(final String columnName, final String where)
            throws Exception {
        Cursor answer = answerForQuery(columnName, where);
        if (answer.moveToNext()) {
            return answer.getString(FIRST_COLUMN);
        }
        throw new Exception("No string.");
    }

    @Override
    public List<Integer> integers(final String columnName, final String where)
            throws Exception {
        Cursor answer = answerForQuery(columnName, where);
        List<Integer> vector = new Vector<>();
        while (answer.moveToNext()) {
            vector.add(answer.getInt(FIRST_COLUMN));
        }
        return vector;
    }
}
