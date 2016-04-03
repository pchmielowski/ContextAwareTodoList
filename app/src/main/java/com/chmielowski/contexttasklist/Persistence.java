package com.chmielowski.contexttasklist;

import java.util.List;

public interface Persistence {
    String string(String columnName, String where) throws Exception;

    List<Integer> integers(String columnName, String where)
            throws Exception;
}
