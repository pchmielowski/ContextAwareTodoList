package com.chmielowski.contexttasklist;

public interface TaskListView {
    void showTask(boolean isDone, String description,
                  ChangeStatusCommand command,
                  DeleteTaskCommand dltCommand);

    void clean();
}
