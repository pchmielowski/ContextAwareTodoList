package com.chmielowski.contexttasklist.sql;

import android.content.ContentValues;

import com.chmielowski.contexttasklist.Persistence;
import com.chmielowski.contexttasklist.TaskList;
import com.chmielowski.contexttasklist.view.TaskListView;

import java.util.List;

public final class SqlTaskList implements TaskList {
    private final int listId;
    private final Persistence tasksDataBase;
    private final Persistence listsDataBase;

    public SqlTaskList(final int id,
                       final Persistence tasksDB,
                       Persistence listsDB) {
        this.tasksDataBase = tasksDB;
        this.listsDataBase = listsDB;
        this.listId = id;
    }

    @Override
    public void showOn(final TaskListView view) throws Exception {
        view.clean();
        for (Integer index : indexes()) {
            new SqlTask(index, tasksDataBase).showOn(view);
        }
    }

    @Override
    public void addTask(final String nm) {
        ContentValues cv = new ContentValues();
        cv.put("name", nm);
        cv.put("done", 0);
        cv.put("list", this.listId);
        tasksDataBase.insert(cv);
    }

    private List<Integer> indexes() throws Exception {
        return tasksDataBase.integers("id", "list=" + this.listId);
    }
}
