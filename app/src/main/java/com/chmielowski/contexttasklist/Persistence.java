package com.chmielowski.contexttasklist;

public interface Persistence {
    Iterable<Integer> getTaskIdxs();

    String taskName(int id) throws Exception;

    Iterable<Task> getTasks();
}
