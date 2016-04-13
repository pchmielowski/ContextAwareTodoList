package com.chmielowski.contexttasklist.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;

import com.chmielowski.contexttasklist.commands.ChangeStatusCommand;

public final class TaskCheckBox { // TODOL refactor completly

    // Task fields
    private final boolean isDone;
    private final String description;
    private final ChangeStatusCommand statusCommand;
    // View
    private final RemoveTaskDialog dialog;
    private final int checkboxViewId;
    private final Context context;

    public TaskCheckBox(final boolean isDone,
                        final String description,
                        final ChangeStatusCommand statusCommand,
                        final RemoveTaskDialog dialog,
                        final int checkboxViewId,
                        final Context context) {
        this.isDone = isDone;
        this.description = description;
        this.statusCommand = statusCommand;
        this.dialog = dialog;
        this.checkboxViewId = checkboxViewId;
        this.context = context;
    }

    @NonNull
    public CheckBox checkBox() {
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
                dialog.show();
                return false; // does not matter
            }
        });
        return task;
    }
}
