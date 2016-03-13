package com.chmielowski.contexttasklist;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.Iterator;

final public class TaskList {
    final private DataBase dataBase;

    public TaskList(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void showOn(TaskListView view) {
        for (Task task : tasks()) {
            task.showOn(view);
        }
    }

    private Iterable<Task> tasks() {
        Cursor taskNames = dataBase.getTaskNames();
        final ArrayList<Task> taskList = new ArrayList<Task>();
        while (taskNames.moveToNext()) {
            final int COLUMN_NUMBER = 0;
            taskList.add(new Task(false, taskNames.getString(COLUMN_NUMBER)));
        }
        return new Iterable<Task>() {
            @Override
            public Iterator<Task> iterator() {
                return taskList.iterator();
            }
        };
    }
}
