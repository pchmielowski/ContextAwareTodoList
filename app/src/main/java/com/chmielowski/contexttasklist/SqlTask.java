package com.chmielowski.contexttasklist;

public final class SqlTask implements Task {

    private static final String COLUMN_NAME = "name";
    private final String CONDITION;
    private final Persistence dataBase;

    public SqlTask(final int i, final Persistence sql) {
        this.CONDITION = "id = " + i;
        this.dataBase = sql;
    }

    @Override
    public void showOn(final TaskListView view) throws Exception {
        view.showTask(isDone(), this.name());
    }

    private Boolean isDone() throws Exception {
        return dataBase.bool(
                this.COLUMN_NAME,
                this.CONDITION);
    }

    private String name() throws Exception {
        return dataBase.string(
                this.COLUMN_NAME,
                this.CONDITION);
    }
}
