package com.chmielowski.contexttasklist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)

public class TaskTest {

    @Mock
    TaskListView view;

    @Test
    public void showOn_withView_callsShowTask() {
        // arrange
        Task task = new Task(false, "task name");

        // act
        task.showOn(view);

        // assert
        verify(view).showTask(false, "task name");
    }

    // TODO: change exception type
    @Test(expected=NullPointerException.class)
    public void showOn_withNull_NpeThrown() {
        // arrange
        Task task = new Task(false, "task name");

        // act & assert
        task.showOn(null);
    }
}