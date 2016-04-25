package com.chmielowski.contexttasklist.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.CheckBox;

public interface ITaskCheckbox {
    @NonNull
    CheckBox showOn(Context context, TaskListView view, int checkboxViewId);
}
