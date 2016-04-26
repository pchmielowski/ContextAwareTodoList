package com.chmielowski.contexttasklist;

import com.chmielowski.contexttasklist.view.ListView;

public interface Task {
    void showOn(ListView view) throws Exception;

    void status(boolean done);

    void delete();
}
