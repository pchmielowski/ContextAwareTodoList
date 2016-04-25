package com.chmielowski.contexttasklist.view;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.chmielowski.contexttasklist.Persistence;
import com.chmielowski.contexttasklist.R;
import com.chmielowski.contexttasklist.TaskList;
import com.chmielowski.contexttasklist.commands.ChangeStatusCommand;
import com.chmielowski.contexttasklist.commands.DeleteTaskCommand;
import com.chmielowski.contexttasklist.sql.DataBase;
import com.chmielowski.contexttasklist.sql.SqlTaskList;

public class TaskViewFragment extends Fragment implements TaskListView {

    private View v;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        v = inflater.inflate(R.layout.task_list, container, false);
        createTasksTable();
        createListsTable();
        Persistence tasksDataBase = new DataBase(v.getContext(), "Tasks");
        Persistence listsDataBase = new DataBase(v.getContext(), "Lists");
        int taskListId = getArguments().getInt("id", 0);;
        final TaskList taskList = new SqlTaskList(taskListId,
                tasksDataBase,
                listsDataBase);
        try {
            taskList.showOn(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final AddTaskDialog dialog = new AddTaskDialog(v.getContext(), this, taskList);
        Button addTaskButton = (Button) v.findViewById(R.id.add_task_button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                dialog.show();
            }
        });
        return v;
    }

    private void createTasksTable() {
        SQLiteOpenHelper sql = new SQLiteOpenHelper(getContext(), "tasks.db", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            }
        };
        sql.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS " +
                "Tasks" +
                " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "done INTEGER," +
                "list INTEGER" +
                ");");
    }
    private void createListsTable() {
        SQLiteOpenHelper sql = new SQLiteOpenHelper(getContext(), "tasks.db", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            }
        };
        sql.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS " +
                "Lists" +
                " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT" +
                ");");
    }

    @Override
    public final void showTask(final boolean isDone,
                               final String description,
                               final ChangeStatusCommand statusCommand,
                               final DeleteTaskCommand deleteCommand) {
        taskListLayout().addView(
                new TaskCheckBox(
                        isDone,
                        description,
                        statusCommand,
                        deleteCommand
                ).showOn(this.v.getContext(), this, View.generateViewId())
        );
    }

    private LinearLayout taskListLayout() {
        return (LinearLayout) v.findViewById(R.id.layout_tasks);
    }

    @Override
    public final void removeTask(final int id) {
        try {
            taskListLayout().removeView(v.findViewById(id));
        } catch (Exception e) {

        }
    }

    @Override
    public final void clean() {
        taskListLayout().removeAllViews();
    }

}
