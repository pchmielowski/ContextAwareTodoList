package com.chmielowski.contexttasklist;

public final class SqlTask implements Task {
    // legacy
    private boolean isDone;
    private String name;

    public SqlTask(final boolean isD, final String nm) {
        this.isDone = isD;
        this.name = nm;
    }

    // new
    private Persistence dataSource;

    public SqlTask(final int i, final Persistence dataSrc) {
        this.dataSource = dataSrc;
    }

    @Override
    public void showOn(final TaskListView view) {
        view.showTask(isDone, name);
    }
}
