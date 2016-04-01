package com.chmielowski.contexttasklist;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.Iterator;

public final class SqlTaskList implements TaskList {
    private final Persistence dataBase;

    public SqlTaskList(final Persistence sql) {
        this.dataBase = sql;
    }

    @Override
    public void showOn(final TaskListView view) throws Exception {
        for (Integer index : indexes()) {
            new SqlTask(index, dataBase).showOn(view);
        }
    }

    private Iterable<Integer> indexes() {
        Cursor answer = dataBase.query("id", "");
        final ArrayList<Integer> tasks = new ArrayList<>();
        while (answer.moveToNext()) {
            final int indexesColumnIdx = 0;
            tasks.add(answer.getInt(indexesColumnIdx));
        }
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return tasks.iterator();
            }
        };
    }
}
