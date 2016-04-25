package com.chmielowski.contexttasklist.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;

import com.chmielowski.contexttasklist.commands.ChangeStatusCommand;
import com.chmielowski.contexttasklist.commands.DeleteTaskCommand;

public final class TaskCheckBox implements ITaskCheckbox {

    private final boolean isDone;
    private final String description;
    private final ChangeStatusCommand statusCommand;
    private final DeleteTaskCommand deleteCommand;

    public TaskCheckBox(final boolean isDone,
                        final String description,
                        final ChangeStatusCommand statusCommand,
                        final DeleteTaskCommand deleteCommand) {
        this.isDone = isDone;
        this.description = description;
        this.statusCommand = statusCommand;
        this.deleteCommand = deleteCommand;
    }

    @NonNull
    @Override
    public CheckBox showOn(final Context context, final TaskListView view, final int checkboxViewId) {
        final CheckBox task = new CheckBox(context);
        task.setChecked(isDone);
        task.setText(description);
        task.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                statusCommand.execute(task.isChecked());
            }
        });
        task.setLongClickable(true);
        task.setId(checkboxViewId);
        task.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                new RemoveTaskDialog(context, view, deleteCommand, checkboxViewId).show();
                return false; // does not matter
            }
        });
        return task;
    }
}
