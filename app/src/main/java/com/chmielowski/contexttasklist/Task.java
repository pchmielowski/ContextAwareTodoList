package com.chmielowski.contexttasklist;

final public class Task {
    private final boolean isDone;
    private final String name;

    public Task(boolean isDone, String name) {
        this.isDone = isDone;
        this.name = name;
    }

    public void showOn(TaskListView view) {
        view.addTask(isDone, name);
    }
}