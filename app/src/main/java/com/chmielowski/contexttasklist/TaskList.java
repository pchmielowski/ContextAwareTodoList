package com.chmielowski.contexttasklist;

public interface TaskList {
    void showOn(TaskListView view) throws Exception;

    void addTask(String nm);
}
