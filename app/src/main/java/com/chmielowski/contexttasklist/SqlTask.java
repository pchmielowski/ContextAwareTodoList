package com.chmielowski.contexttasklist;

public final class SqlTask implements Task {

    private final Persistence dataBase;
    private final int id;

    public SqlTask(final int i, final Persistence sql) {
        this.id = i;
        this.dataBase = sql;
    }

    @Override
    public void showOn(final TaskListView view) throws Exception {
        view.showTask(true, this.name());
    }

    private String name() throws Exception {
        return dataBase.string(
                "name",
                "id = " + id
        );
    }
}
