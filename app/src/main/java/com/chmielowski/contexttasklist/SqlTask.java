package com.chmielowski.contexttasklist;

import android.view.View;

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
                new View.OnClickListener() {
                    public void onClick(final View v) {
                        if (isDone) {
                            unsetDone();
                        } else {
                            setDone();
                        }
                    }
                });
    }

    @Override
    public void setDone() {
        dataBase.setBool(true, this.condition);
    }

    @Override
    public void unsetDone() {
        dataBase.setBool(false, this.condition);
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
