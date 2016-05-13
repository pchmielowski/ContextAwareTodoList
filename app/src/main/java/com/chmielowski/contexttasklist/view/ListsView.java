package com.chmielowski.contexttasklist.view;

public interface ListsView {
    void removeCurrentTab() throws Exception;

    void addTaskList(String name) throws Exception;
}
