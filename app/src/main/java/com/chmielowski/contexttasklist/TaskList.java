package com.chmielowski.contexttasklist;

import com.chmielowski.contexttasklist.view.TaskListView;

public interface TaskList {
    void showOn(TaskListView view) throws Exception;

    void addTask(String nm);
}
