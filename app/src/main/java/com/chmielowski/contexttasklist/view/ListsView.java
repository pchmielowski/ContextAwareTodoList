package com.chmielowski.contexttasklist.view;

public interface ListsView {
    void removeCurrentTab();

    void addTaskList(String name) throws Exception;
}
