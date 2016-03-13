package com.chmielowski.contexttasklist;

import android.content.Context;
import android.database.Cursor;

final public class TaskList {
    public Context context; // TODO: remove

    public void showOn(TaskListView view) {

        TodoDbAdapter baseAdapter = new TodoDbAdapter(context);
        Cursor cursor = baseAdapter.getAllTasks();
        while (cursor.moveToNext()) {
            Task task = new Task(false, cursor.getString(1));
            task.showOn(view);
        }
    }
}
