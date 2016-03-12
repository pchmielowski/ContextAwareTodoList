package com.chmielowski.contexttasklist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private TaskList taskList = new TaskList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList.addObserver(this);

        taskList.add("Programming");
        taskList.add("Cleaning");
        taskList.add("Shopping");
    }

    public void addCheckox(boolean isChecked, String description) {
        CheckBox task = new CheckBox(this);
        task.setChecked(isChecked);
        task.setText(description);

        LinearLayout taskList = (LinearLayout) findViewById(R.id.taskList);
        taskList.addView(task);
    }

    @Override
    public void update(Observable observable, Object data) {
        addCheckox(false, (String)data);
    }
}
