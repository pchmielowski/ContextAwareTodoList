package com.chmielowski.contexttasklist.view.dialog;

import android.content.Context;

import com.chmielowski.contexttasklist.view.TaskListView;
import com.chmielowski.contexttasklist.view.dialog.QuestionDialog;

public abstract class TaskListDialog implements QuestionDialog {
    protected final TaskListView view;
    protected final Context context;

    public TaskListDialog(final Context ctx, final TaskListView vw) {
        this.context = ctx;
        this.view = vw;
    }
}
