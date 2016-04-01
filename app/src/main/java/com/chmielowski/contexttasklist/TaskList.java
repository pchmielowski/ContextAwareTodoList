package com.chmielowski.contexttasklist;

public final class TaskList implements ITaskList {
    private final Persistence dataBase;

    public TaskList(final Persistence dB) {
        this.dataBase = dB;
    }

    @Override
    public void showOn(final TaskListView view) {
        for (Task task : dataBase.getTasks()) {
            task.showOn(view);
        }
    }
}
