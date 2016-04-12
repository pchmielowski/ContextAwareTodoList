package com.chmielowski.contexttasklist;

public final class SqlTask implements Task {

    private final String condition;
    private final Persistence dataBase;

    public SqlTask(final int i, final Persistence sql) {
        this.condition = "id = " + i;
        this.dataBase = sql;
    }

    @Override
    public void showOn(final TaskListView view) throws Exception {
        view.showTask(
                this.isDone(),
                this.name(),
                new ChangeTaskStatusCommand(this),
                new DeleteTaskCommand(this)
        );
    }

    @Override
    public void status(final boolean done) {
        dataBase.setBool(done, this.condition);
    }

    @Override
    public void delete() {
        dataBase.delete(this.condition);
    }

    private Boolean isDone() throws Exception {
        final String columnName = "done";
        return dataBase.bool(
                columnName,
                this.condition);
    }

    private String name() throws Exception {
        final String columnName = "name";
        return dataBase.string(
                columnName,
                this.condition);
    }
}
