package com.chmielowski.contexttasklist;

public interface Task {
    void showOn(TaskListView view) throws Exception;

    void status(boolean done);

    void delete();
}
