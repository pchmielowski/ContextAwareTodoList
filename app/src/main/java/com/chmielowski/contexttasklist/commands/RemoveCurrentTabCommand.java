package com.chmielowski.contexttasklist.commands;


import com.chmielowski.contexttasklist.view.ListsView;

final public class RemoveCurrentTabCommand implements Command {
    private final ListsView view;

    public RemoveCurrentTabCommand(ListsView view) {
        this.view = view;
    }

    @Override
    public void execute() throws Exception {
        view.removeCurrentTab();
    }
}
