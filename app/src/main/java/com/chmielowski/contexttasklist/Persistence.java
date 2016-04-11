package com.chmielowski.contexttasklist;

import android.content.ContentValues;

import java.util.List;

public interface Persistence {
    String string(String columnName, String where) throws Exception;

    List<Integer> integers(String columnName, String where)
            throws Exception;

    Boolean bool(String columnName, String where) throws Exception;

    void setBool(boolean b, String condition);

    void insert(ContentValues cv);

    void delete(String where);
}
