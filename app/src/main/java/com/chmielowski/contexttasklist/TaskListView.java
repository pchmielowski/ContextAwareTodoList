package com.chmielowski.contexttasklist;

public interface TaskListView {
    void showTask(boolean isDone, String description,
                  ToggleStatusCommand command);
}
