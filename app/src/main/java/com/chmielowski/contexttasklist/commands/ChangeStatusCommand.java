package com.chmielowski.contexttasklist.commands;

public interface ChangeStatusCommand {
    void execute(boolean done);
}
