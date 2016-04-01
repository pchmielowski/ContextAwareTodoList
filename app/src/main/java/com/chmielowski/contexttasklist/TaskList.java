package com.chmielowski.contexttasklist;

public final class TaskList implements ITaskList {
    private final Persistence dataBase;

    public TaskList(final Persistence dB) {
        this.dataBase = dB;
    }

    @Override
    public void showOn(final TaskListView view) throws Exception {
        for (Integer id : dataBase.getTaskIdxs()) {
            new SqlTask(id,dataBase).showOn(view);
        }
    }
}
