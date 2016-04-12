package com.chmielowski.contexttasklist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
        final CheckBox task = new CheckBox(this);
        task.setChecked(isDone);
        task.setText(description);
        task.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                statusCommand.execute(task.isChecked());
            }
        });
        task.setLongClickable(true);
        final int id = View.generateViewId();
        task.setId(id);
        final AlertDialog dialog = removeTaskDialog(deleteCommand, id);
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

    private AlertDialog removeTaskDialog(final DeleteTaskCommand deleteCommand,
                                         final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete task?");
        final TaskListView view = (TaskListView) this;
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                LinearLayout taskList =
                        (LinearLayout) findViewById(R.id.taskListLayout);
                taskList.removeView(findViewById(id));
                deleteCommand.execute();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    @Override
    public final void clean() {
        LinearLayout taskList =
                (LinearLayout) findViewById(R.id.taskListLayout);
        taskList.removeAllViews();
    }
}
