package com.chmielowski.contexttasklist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class SqlTaskTest {

    @Mock
    TaskListView view;

    @Mock
    Persistence dataBase;

    @Test
    public void showsValidTaskName() throws Exception {
        // arrange
        when(dataBase.string("Name", "id = 123")).thenReturn("testing");
        SqlTask task = new SqlTask(123, dataBase);

        // act
        task.showOn(view);

        // assert
        verify(view).showTask(true, "testing");
    }
}
