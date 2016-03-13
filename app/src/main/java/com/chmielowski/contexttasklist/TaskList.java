package com.chmielowski.contexttasklist;

import android.content.Context;

final public class TaskList {
    public Context context; // TODO: remove

    public void showOn(TaskListView view) {
        DataBase dataBase = new DataBase(context);
        for (Task task : dataBase.tasks()) {
            task.showOn(view);
        }
    }
}
