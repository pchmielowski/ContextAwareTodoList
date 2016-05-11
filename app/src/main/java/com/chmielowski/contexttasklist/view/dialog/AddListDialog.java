package com.chmielowski.contexttasklist.view.dialog;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.text.InputType;
import android.widget.EditText;

import com.chmielowski.contexttasklist.MainActivity;
import com.chmielowski.contexttasklist.sql.SqlPersistence;

public final class AddListDialog
        implements QuestionDialog {

    private final MainActivity activity;
    private final Context context;
    private final TabLayout tabs;

    public AddListDialog(final MainActivity mainActivity,
                         final Context ctx,
                         final TabLayout tabLayout) {
        this.activity = mainActivity;
        this.context = ctx;
        this.tabs = tabLayout;
    }

    @Override
    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Add new list");
        final EditText input = new EditText(this.context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                try {
                    createTaskList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void createTaskList() throws Exception {
                ContentValues cv = new ContentValues();
                cv.put("name", String.valueOf(input.getText()));
                new SqlPersistence(
                        activity.getApplicationContext(),
                        "Lists")
                        .insert(cv);
                // ToDo: refactor to create TaskList and let it show on view
                activity.addTaskList(String.valueOf(input.getText()));
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
