package com.chmielowski.contexttasklist;

import android.database.Cursor;

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
        Cursor answer = dataBase.query(
                "Name",
                "id = " + id
        );
        if (answer.moveToNext()) {
            final int nameColumnIdx = 0;
            return answer.getString(nameColumnIdx);
        }
        throw new Exception("No task with id: " + Integer.toString(id));
    }
}
