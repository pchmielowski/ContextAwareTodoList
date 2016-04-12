package com.chmielowski.contexttasklist;

import com.chmielowski.contexttasklist.view.TaskListView;

public interface Task {
    void showOn(TaskListView view) throws Exception;

    void status(boolean done);

    void delete();
}
