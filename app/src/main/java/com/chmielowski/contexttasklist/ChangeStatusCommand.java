package com.chmielowski.contexttasklist;

public interface ChangeStatusCommand {
    void execute(boolean done);
}
