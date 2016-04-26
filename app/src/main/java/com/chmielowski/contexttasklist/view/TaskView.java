package com.chmielowski.contexttasklist.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.CheckBox;

public interface TaskView {
    @NonNull
    CheckBox showOn(Context context, ListView view, int checkboxViewId);
}
