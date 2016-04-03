package com.chmielowski.contexttasklist;

public final class ToggleTaskStatusCommand implements ToggleStatusCommand {
    private final Task task;

    public ToggleTaskStatusCommand(final Task tsk) {
        this.task = tsk;
    }

    @Override
    public void execute(final boolean done) {
        task.toggleStatus(done);
    }
}
