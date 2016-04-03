package com.chmielowski.contexttasklist;

public class ToggleStatusCommand implements Command {
    private final Task task;

    public ToggleStatusCommand(Task tsk) {
        this.task = tsk;
    }

    @Override
    public void execute(boolean done) {
        task.toggleStatus(done);
    }
}
