package com.chmielowski.contexttasklist;

public class DeleteTaskCommand implements Command {

    private final Task task;

    public DeleteTaskCommand(Task tsk) {
        this.task = tsk;
    }

    @Override
    public void execute() {
        this.task.delete();
    }
}
