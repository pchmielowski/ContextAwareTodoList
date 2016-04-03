package com.chmielowski.contexttasklist;

import android.database.Cursor;

public interface Persistence {
    Cursor query(String columnName, String where);

    String queryForString(String columnName, String where) throws Exception;
}
