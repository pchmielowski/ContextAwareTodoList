package com.chmielowski.contexttasklist.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.chmielowski.contexttasklist.Persistence;

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
        getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + tableName + ";");
        getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS " +
                this.tableName +
                " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "done INTEGER" +
                ");");
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
                        String where) throws Exception {
        if (where.isEmpty()) {
            where = "1";
        }
        Cursor answer = answerForQuery(columnName,
                where + " AND " + columnName + "=1");
        int numOccurences = answer.getCount();
        if (numOccurences > 1) {
            throw new IllegalArgumentException(
                    "More than one entries fulfil condition: " + where);
        }
        return numOccurences == 1;
    }

    @Override
    public void setBool(final boolean b, final String condition) {
        ContentValues value = new ContentValues();
        value.put("done", b);
        getWritableDatabase().update(tableName, value, condition, null);
    }

    @Override
    public void insert(final ContentValues cv) {
        getWritableDatabase().insert(tableName, null, cv);
    }

    @Override
    public void delete(final String where) {
        getWritableDatabase().delete(tableName, where, null);
    }
}
