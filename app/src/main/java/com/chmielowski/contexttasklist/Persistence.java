package com.chmielowski.contexttasklist;

public interface Persistence {
    Iterable<Task> getTasks();
}
