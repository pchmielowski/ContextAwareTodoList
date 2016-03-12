package com.chmielowski.contexttasklist;

import java.util.Observable;

final public class TaskList extends Observable
{
    public void add(String task)
    {
        setChanged();
        notifyObservers(task);
    }
}
