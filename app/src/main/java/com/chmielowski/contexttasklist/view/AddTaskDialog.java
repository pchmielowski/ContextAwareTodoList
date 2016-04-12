package com.chmielowski.contexttasklist.view;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;

import com.chmielowski.contexttasklist.TaskList;

public final class AddTaskDialog implements QuestionDialog {
    private final Context context;
    private final TaskListView view;
    private final TaskList taskList;

    public AddTaskDialog(final Context ctx,
                         final TaskListView vw,
                         final TaskList tlist) {
        this.context = ctx;
        this.view = vw;
        this.taskList = tlist;
    }

    @Override
    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Add new task");
        final EditText input = new EditText(this.context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        final TaskListView view = this.view;
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                taskList.addTask(input.getText().toString());
                try {
                    taskList.showOn(view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog,
                                        final int which) {
                        dialog.cancel();
                    }
                });
        builder.create().show();
    }
}
