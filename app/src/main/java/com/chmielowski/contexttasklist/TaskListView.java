package com.chmielowski.contexttasklist;

import android.view.View;

public interface TaskListView {
    void showTask(boolean isDone, String description,
                  View.OnClickListener listener);
}
