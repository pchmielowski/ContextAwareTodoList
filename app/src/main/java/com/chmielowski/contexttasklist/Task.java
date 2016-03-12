package com.chmielowski.contexttasklist;

final public class Task {
    private final String name;

    public Task(String name)
    {
        this.name = name;
    }

    public void showOn(TaskListView view) {
        view.addCheckbox(false, name);
    }
}