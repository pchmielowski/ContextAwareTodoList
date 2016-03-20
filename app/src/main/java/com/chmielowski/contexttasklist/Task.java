package com.chmielowski.contexttasklist;

public final class Task implements ITask {
    private final boolean isDone;
    private final String name;

    public Task(final boolean isD, final String nm) {
        this.isDone = isD;
        this.name = nm;
    }

    @Override
    public void showOn(final TaskListView view) {
        view.showTask(isDone, name);
    }
}
