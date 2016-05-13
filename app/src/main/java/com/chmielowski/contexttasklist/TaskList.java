package com.chmielowski.contexttasklist;

import com.chmielowski.contexttasklist.view.ListView;
import com.chmielowski.contexttasklist.view.ListsView;

public interface TaskList {
    void showTasksOn(ListView view) throws Exception;

    void showOn(ListsView view) throws Exception;

    void addTask(String nm);

    void remove() throws Exception;
}
