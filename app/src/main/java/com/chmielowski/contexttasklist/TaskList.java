package com.chmielowski.contexttasklist;

import com.chmielowski.contexttasklist.view.ListView;

public interface TaskList {
    void showOn(ListView view) throws Exception;

    void addTask(String nm);
}
