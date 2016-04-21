package com.chmielowski.contexttasklist.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
        Persistence dataBase = new DataBase(v.getContext(), "Tasks");
        final TaskList taskList = new SqlTaskList(dataBase);
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

//    public final void addTaskChbx(TaskCheckBox taskCheckBox) {
//        int checkboxViewId = View.generateViewId();
//        TaskListView taskListView = (TaskListView) this;
//        taskListLayout().addView(taskCheckBox.checkBox());
//    }

    @Override
    public final void showTask(final boolean isDone,
                               final String description,
                               final ChangeStatusCommand statusCommand,
                               final DeleteTaskCommand deleteCommand) {
        int checkboxViewId = View.generateViewId();
        TaskListView taskListView = (TaskListView) this;
        taskListLayout().addView(
                new TaskCheckBox(
                        isDone,
                        description,
                        statusCommand,
                        new RemoveTaskDialog(
                                this.v.getContext(),
                                taskListView,
                                deleteCommand,
                                checkboxViewId),
                        checkboxViewId,
                        this.v.getContext()
                ).checkBox()
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
