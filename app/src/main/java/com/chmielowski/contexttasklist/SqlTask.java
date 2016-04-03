package com.chmielowski.contexttasklist;

public final class SqlTask implements Task {

    private static final String COLUMN_NAME = "name";
    private final String condition;
    private final Persistence dataBase;

    public SqlTask(final int i, final Persistence sql) {
        this.condition = "id = " + i;
        this.dataBase = sql;
    }

    @Override
    public void showOn(final TaskListView view) throws Exception {
        final boolean isDone = this.isDone();
        view.showTask(
                isDone,
                this.name(),
                new ToggleTaskStatusCommand(this));
    }

    @Override
    public void toggleStatus(final boolean done) {
        dataBase.setBool(done, this.condition);
    }

    private Boolean isDone() throws Exception {
        return dataBase.bool(
                this.COLUMN_NAME,
                this.condition);
    }

    private String name() throws Exception {
        return dataBase.string(
                this.COLUMN_NAME,
                this.condition);
    }
}
