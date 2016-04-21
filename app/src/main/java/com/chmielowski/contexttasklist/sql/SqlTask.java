package com.chmielowski.contexttasklist.sql;

import com.chmielowski.contexttasklist.Persistence;
import com.chmielowski.contexttasklist.Task;
import com.chmielowski.contexttasklist.commands.ChangeTaskStatusCommand;
import com.chmielowski.contexttasklist.commands.DeleteTaskCommand;
import com.chmielowski.contexttasklist.view.TaskListView;

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

//    public void addToView(final TaskListView view) throws Exception {
//        int checkboxViewId = View.generateViewId(); // out of responsibility
//        return new TaskCheckBox(
//                this.isDone(),
//                this.name(),
//                new ChangeTaskStatusCommand(this),
//                new RemoveTaskDialog(
//                        this.v.getContext(),  // out of responsibility
//                        view, // may be out of responsibility
//                        new DeleteTaskCommand(this),
//                        checkboxViewId),  // out of responsibility
//                checkboxViewId,  // out of responsibility
//                this.v.getContext()  // out of responsibility
//        );

//        should look similar to one of these. I think, third is OK, but let's start with first!
//  1.
//        this.isDone(),
//        this.name(),
//        new ChangeTaskStatusCommand(this),
//        new DeleteTaskCommand(this)
//  2.
//        return view.checkBox().withStatus(this.isDone()).withName(this.name()).withChangeTaskStatusCommand(new ...).withDeleteTaskCommand(new ...);
//  3.
//        return new ChangeableChbx(
//                   new DeletableChbx(
//                         view.checkBox(isDone, name),
//                         new DeleteTaskCommand()),
//                   new ChangeTaskCommand())
//   Q: return to TaskList and let him add to view OR add to view on Task's own
//    }

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
