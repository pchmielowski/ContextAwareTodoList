package com.chmielowski.contexttasklist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements TaskListView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Task t1 = new Task("programming");
        Task t2 = new Task("shopping");

        t1.showOn(this);
        t2.showOn(this);
    }

    @Override
    public void addCheckbox(boolean isChecked, String description) {
        CheckBox task = new CheckBox(this);
        task.setChecked(isChecked);
        task.setText(description);

        LinearLayout taskList = (LinearLayout) findViewById(R.id.taskList);
        taskList.addView(task);
    }
}
