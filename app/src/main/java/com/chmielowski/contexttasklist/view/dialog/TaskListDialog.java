package com.chmielowski.contexttasklist.view.dialog;

import android.content.Context;

import com.chmielowski.contexttasklist.view.ListView;

public abstract class TaskListDialog implements QuestionDialog {
    protected final ListView view;
    protected final Context context;

    public TaskListDialog(final Context ctx, final ListView vw) {
        this.context = ctx;
        this.view = vw;
    }
}
