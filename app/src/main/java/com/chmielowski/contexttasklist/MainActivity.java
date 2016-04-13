package com.chmielowski.contexttasklist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chmielowski.contexttasklist.sql.DataBase;
import com.chmielowski.contexttasklist.sql.SqlTaskList;
import com.chmielowski.contexttasklist.view.AddTaskDialog;
import com.chmielowski.contexttasklist.view.TaskListView;
import com.chmielowski.contexttasklist.view.TaskListViewImpl;


public class MainActivity extends AppCompatActivity{
    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Persistence dataBase = new DataBase(this, "Tasks");
        final TaskList taskList = new SqlTaskList(dataBase);
        final TaskListView taskListView = new TaskListViewImpl(this);
        try {
            taskList.showOn(taskListView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final AddTaskDialog dialog = new AddTaskDialog((Context)this, taskListView, taskList);
        Button addTaskButton = (Button) findViewById(R.id.add_task_button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                dialog.show();
            }
        });
    }
}
