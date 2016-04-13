package com.chmielowski.contexttasklist.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.chmielowski.contexttasklist.R;
import com.chmielowski.contexttasklist.commands.ChangeStatusCommand;
import com.chmielowski.contexttasklist.commands.DeleteTaskCommand;

public class TaskListViewImpl implements TaskListView {
    private final Activity mainActivity;

    public TaskListViewImpl(final Activity mainActvt) {

        this.mainActivity = mainActvt;
    }

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
                                (Context)mainActivity,
                                taskListView,
                                deleteCommand,
                                checkboxViewId),
                        checkboxViewId,
                        (Context)mainActivity
                ).checkBox()
        );
    }

    private LinearLayout taskListLayout() {
        return (LinearLayout) mainActivity.findViewById(R.id.taskListLayout);
    }

    @Override
    public final void removeTask(final int id) {
        taskListLayout().removeView(mainActivity.findViewById(id));
    }

    @Override
    public final void clean() {
        taskListLayout().removeAllViews();
    }
}
