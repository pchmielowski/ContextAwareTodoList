package com.chmielowski.contexttasklist;

final public class TaskList {
    final private DataBase dataBase;

    public TaskList(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void showOn(TaskListView view) {
        for (Task task : dataBase.getTasks()) {
            task.showOn(view);
        }
    }
}
