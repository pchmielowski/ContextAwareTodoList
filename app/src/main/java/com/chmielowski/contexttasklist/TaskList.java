package com.chmielowski.contexttasklist;

final public class TaskList {
    public void showOn(TaskListView view) {
        Task programming = new Task(false, "programming");
        programming.showOn(view);

        Task shopping = new Task(true, "shopping");
        shopping.showOn(view);
    }
}
