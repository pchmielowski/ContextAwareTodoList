package com.chmielowski.contexttasklist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements TaskListView {
    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            new SqlTaskList(new DataBase(this)).showOn(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void showTask(final boolean isDone,
                               final String description,
                               final Command command) {
        final CheckBox task = new CheckBox(this);
        task.setChecked(isDone);
        task.setText(description);
        task.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                command.execute(task.isChecked());
            }
        });
        LinearLayout taskList =
                (LinearLayout) findViewById(R.id.taskListLayout);
        taskList.addView(task);
    }
}
