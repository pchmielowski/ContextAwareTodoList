package com.chmielowski.contexttasklist;

final public class TaskList {
    public void showOn(TaskListView view) {
        Task t1 = new Task(false, "programming");
        t1.showOn(view);

        Task t2 = new Task(true, "shopping");
        t2.showOn(view);
    }
}
