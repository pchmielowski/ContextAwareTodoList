package com.chmielowski.contexttasklist;

public final class ChangeTaskStatusCommand implements ChangeStatusCommand {
    private final Task task;

    public ChangeTaskStatusCommand(final Task tsk) {
        this.task = tsk;
    }

    @Override
    public void execute(final boolean done) {
        task.status(done);
    }
}
