package com.chmielowski.contexttasklist;

import android.content.ContentValues;

import java.util.List;

public final class SqlTaskList implements TaskList {
    private final Persistence dataBase;

    public SqlTaskList(final Persistence sql) {
        this.dataBase = sql;
    }

    @Override
    public void showOn(final TaskListView view) throws Exception {
        view.clean();
        for (Integer index : indexes()) {
            new SqlTask(index, dataBase).showOn(view);
        }
    }

    @Override
    public void addTask(String nm) {
        ContentValues cv = new ContentValues();
        cv.put("name", nm);
        cv.put("done", 0);
        dataBase.insert(cv);
    }

    private List<Integer> indexes() throws Exception {
        return dataBase.integers("id", "");
    }
}
