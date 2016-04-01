package com.chmielowski.contexttasklist;

import android.database.Cursor;

public final class SqlTask implements Task {

    private final Persistence dataSource;
    private final int id;

    public SqlTask(final int i, final Persistence dataSrc) {
        this.id = i;
        this.dataSource = dataSrc;
    }

    @Override
    public void showOn(final TaskListView view) throws Exception {
        Boolean isDone = true;
        String name = taskName(this.id);


        view.showTask(isDone, name);
    }

    private String taskName(int id) throws Exception {
        Cursor query = dataSource.query(
                "Name",
                "id = " + id
        );
        if (query.moveToNext()) {
            final int taskNamesColumn = 0;
            return query.getString(taskNamesColumn);
        }
        throw new Exception("No task with id: " + Integer.toString(id));
    }
}
