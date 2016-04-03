package com.chmielowski.contexttasklist;

public final class ToggleStatusCommand implements Command {
    private final Task task;

    public ToggleStatusCommand(final Task tsk) {
        this.task = tsk;
    }

    @Override
    public void execute(final boolean done) {
        task.toggleStatus(done);
    }
}
