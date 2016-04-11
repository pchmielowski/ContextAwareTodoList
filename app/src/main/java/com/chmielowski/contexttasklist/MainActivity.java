package com.chmielowski.contexttasklist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add new task");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        final TaskListView view = (TaskListView) this;
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                taskList.addTask(input.getText().toString());
                try {
                    taskList.showOn(view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog,
                                        final int which) {
                        dialog.cancel();
                    }
                });
        final AlertDialog dialog = builder.create();
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
                               final ChangeStatusCommand command) {
        final CheckBox task = new CheckBox(this);
        task.setChecked(isDone);
        task.setText(description);
        task.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                command.execute(task.isChecked());
            }
        });
        task.setLongClickable(true);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete task?");
        final TaskListView view = (TaskListView) this;
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.cancel();
            }
        });
        final AlertDialog dialog = builder.create();


        task.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                dialog.show();
                return false;
            }
        });
        LinearLayout taskList =
                (LinearLayout) findViewById(R.id.taskListLayout);
        taskList.addView(task);
    }

    @Override
    public final void clean() {
        LinearLayout taskList =
                (LinearLayout) findViewById(R.id.taskListLayout);
        for (int i = 1; i < taskList.getChildCount(); i++) { // i = 1 to omit button. TODO: move button to another layout
            View child = taskList.getChildAt(i);
            taskList.removeView(child);
        }
//        taskList.removeAllViews();
    }
}
