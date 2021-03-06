package com.chmielowski.contexttasklist.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;

import com.chmielowski.contexttasklist.commands.ChangeStatusCommand;
import com.chmielowski.contexttasklist.commands.DeleteTaskCommand;
import com.chmielowski.contexttasklist.view.dialog.RemoveTaskDialog;

public final class CheckboxTaskView implements TaskView {

    private final boolean isDone;
    private final String description;
    private final ChangeStatusCommand statusCommand;
    private final DeleteTaskCommand deleteCommand;

    public CheckboxTaskView(final boolean isDn,
                            final String descript,
                            final ChangeStatusCommand statusCmd,
                            final DeleteTaskCommand deleteCmd) {
        this.isDone = isDn;
        this.description = descript;
        this.statusCommand = statusCmd;
        this.deleteCommand = deleteCmd;
    }

    @NonNull
    @Override
    public CheckBox showOn(final Context context,
                           final ListView view,
                           final int checkboxViewId) {
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
                new RemoveTaskDialog(context,
                        view,
                        deleteCommand,
                        checkboxViewId).show();
                return false; // does not matter
            }
        });
        return task;
    }
}
