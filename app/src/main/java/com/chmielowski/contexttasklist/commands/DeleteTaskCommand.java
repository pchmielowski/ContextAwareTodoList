package com.chmielowski.contexttasklist;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "task")
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
