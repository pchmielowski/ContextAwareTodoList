package com.chmielowski.contexttasklist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements TaskListView {
    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new TaskList(new DataBase(this)).showOn(this);
    }

    @Override
    public final void showTask(final boolean isDone, final String description) {
        CheckBox task = new CheckBox(this);
        task.setChecked(isDone);
        task.setText(description);
        LinearLayout taskList =
                (LinearLayout) findViewById(R.id.taskListLayout);
        taskList.addView(task);
    }
}
