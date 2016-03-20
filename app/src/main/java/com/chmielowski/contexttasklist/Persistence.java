package com.chmielowski.contexttasklist;

public interface Persistence {
    Iterable<ITask> getTasks();
}
