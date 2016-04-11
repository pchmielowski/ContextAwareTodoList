package com.chmielowski.contexttasklist;

public final class DeleteTaskCommand implements Command {

    private final Task task;

    public DeleteTaskCommand(final Task tsk) {
        this.task = tsk;
    }

    @Override
    public void execute() {
        this.task.delete();
    }
}
