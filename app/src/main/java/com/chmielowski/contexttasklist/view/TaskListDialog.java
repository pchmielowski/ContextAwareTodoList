package com.chmielowski.contexttasklist.view;

import android.content.Context;

public abstract class TaskListDialog implements QuestionDialog  {
    protected final TaskListView view;
    protected final Context context;

    public TaskListDialog(final Context ctx, final TaskListView vw) {
        this.context = ctx;
        this.view = vw;
    }
}
