package com.chmielowski.contexttasklist.view;


import com.chmielowski.contexttasklist.commands.ChangeStatusCommand;
import com.chmielowski.contexttasklist.commands.DeleteTaskCommand;

public interface TaskListView {
    void showTask(boolean isDone, String description,
                  ChangeStatusCommand command,
                  DeleteTaskCommand dltCommand);

    void removeTask(int id);

    void clean();
}
