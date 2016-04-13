package com.chmielowski.contexttasklist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.chmielowski.contexttasklist.commands.ChangeStatusCommand;
import com.chmielowski.contexttasklist.commands.DeleteTaskCommand;
import com.chmielowski.contexttasklist.sql.DataBase;
import com.chmielowski.contexttasklist.sql.SqlTaskList;
import com.chmielowski.contexttasklist.view.AddTaskDialog;
import com.chmielowski.contexttasklist.view.RemoveTaskDialog;
import com.chmielowski.contexttasklist.view.TaskListView;


public class MainActivity extends AppCompatActivity implements TaskListView {
    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Persistence dataBase = new DataBase(this, "Tasks");
        final TaskList taskList = new SqlTaskList(dataBase);
        try {
            taskList.showOn(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final AddTaskDialog dialog = new AddTaskDialog(this, this, taskList);
        Button addTaskButton = (Button) findViewById(R.id.add_task_button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                dialog.show();
            }
        });
    }

    @Override
    public final void showTask(final boolean isDone,
                               final String description,
                               final ChangeStatusCommand statusCommand,
                               final DeleteTaskCommand deleteCommand) {
        taskListLayout().addView(
                checkBox(
                        isDone,
                        description,
                        statusCommand,
                        deleteCommand,
                        View.generateViewId()
                )
        );
    }

    private LinearLayout taskListLayout() {
        return (LinearLayout) findViewById(R.id.taskListLayout);
    }

    @NonNull
    private CheckBox checkBox(final boolean isDone,
                              final String description,
                              final ChangeStatusCommand statusCommand,
                              final DeleteTaskCommand deleteCommand,
                              int checkboxViewId) {
        final CheckBox task = new CheckBox(this);
        task.setChecked(isDone);
        task.setText(description);
        task.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                statusCommand.execute(task.isChecked());
            }
        });
        task.setLongClickable(true);
        task.setId(checkboxViewId);

        final RemoveTaskDialog dialog = new RemoveTaskDialog(
                this,
                this,
                deleteCommand,
                checkboxViewId);

        task.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                dialog.show();
                return false;
            }
        });
        return task;
    }

    @Override
    public final void removeTask(final int id) {
        taskListLayout().removeView(findViewById(id));
    }

    @Override
    public final void clean() {
        taskListLayout().removeAllViews();
    }
}
