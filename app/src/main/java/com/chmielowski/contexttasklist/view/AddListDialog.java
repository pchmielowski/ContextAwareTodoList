package com.chmielowski.contexttasklist.view;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.text.InputType;
import android.widget.EditText;

public final class AddListDialog
        implements QuestionDialog {

    private final Context context;
    private final TabLayout tabs;

    public AddListDialog(Context ctx, TabLayout tabLayout) {
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
                tabs.addTab(tabs.newTab().setText(input.getText()));
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
