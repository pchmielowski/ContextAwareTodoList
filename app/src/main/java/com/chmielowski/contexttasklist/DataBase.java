package com.chmielowski.contexttasklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;
import java.util.Vector;

public final class DataBase extends SQLiteOpenHelper implements Persistence {

    private final String tableName;
    private static final int FIRST_COLUMN = 0;

    public DataBase(final Context context, final String tblName) {
        super(context, "tasks.db", null, 1);
        this.tableName = tblName;
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db,
                          final int oldVersion,
                          final int newVersion) {
        throw new UnsupportedOperationException();
    }

    private Cursor answerForQuery(final String columnName, final String where) {
        return getReadableDatabase().query(
                tableName,
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

    @Override
    public Boolean bool(final String columnName,
                        final String where) throws Exception {
        Cursor answer = answerForQuery(columnName, where + " AND " + columnName +"=1");
        if (answer.moveToNext()) {
            return true;
        }
        return false;
    }

    @Override
    public void setBool(final boolean b, final String condition) {
        ContentValues value = new ContentValues();
        value.put("done", b);
        getWritableDatabase().update(tableName, value, condition, null);
    }
}
