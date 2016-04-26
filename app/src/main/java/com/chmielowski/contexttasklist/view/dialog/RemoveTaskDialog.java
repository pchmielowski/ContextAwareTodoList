package com.chmielowski.contexttasklist.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.chmielowski.contexttasklist.commands.DeleteTaskCommand;
import com.chmielowski.contexttasklist.view.ListView;

public final class RemoveTaskDialog
        extends TaskListDialog
        implements QuestionDialog {

    private final DeleteTaskCommand deleteCommand;
    private final int checkBoxId;

    public RemoveTaskDialog(final Context ctx,
                            final ListView vw,
                            final DeleteTaskCommand deleteCmd,
                            final int chxkBxId) {
        super(ctx, vw);
        this.deleteCommand = deleteCmd;
        this.checkBoxId = chxkBxId;
    }

    @Override
    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Delete task?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                deleteCommand.execute();
                view.removeTask(checkBoxId);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

}
