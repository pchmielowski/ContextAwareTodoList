package com.chmielowski.contexttasklist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.chmielowski.contexttasklist.commands.ChangeStatusCommand;
import com.chmielowski.contexttasklist.commands.DeleteTaskCommand;
import com.chmielowski.contexttasklist.sql.DataBase;
import com.chmielowski.contexttasklist.sql.SqlTaskList;
import com.chmielowski.contexttasklist.view.AddTaskDialog;
import com.chmielowski.contexttasklist.view.RemoveTaskDialog;
import com.chmielowski.contexttasklist.view.TaskCheckBox;
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
        int checkboxViewId = View.generateViewId();
        Context mainActivityContext = (Context) this;
        TaskListView taskListView = (TaskListView) this;
        taskListLayout().addView(
                new TaskCheckBox(
                        isDone,
                        description,
                        statusCommand,
                        new RemoveTaskDialog(
                                mainActivityContext,
                                taskListView,
                                deleteCommand,
                                checkboxViewId),
                        checkboxViewId,
                        mainActivityContext
                ).checkBox()
        );
    }

    private LinearLayout taskListLayout() {
        return (LinearLayout) findViewById(R.id.taskListLayout);
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
