package com.chmielowski.contexttasklist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class SqlTaskTest {

    @Mock
    TaskListView view;

    @Mock
    Persistence dataBase;

    @Test
    public void callsMethod_view_showTask() throws Exception {
        // arrange
        when(dataBase.string(anyString(), anyString())).thenReturn("testing");
        when(dataBase.bool(anyString(), anyString())).thenReturn(true);
        SqlTask task = new SqlTask(123, dataBase);

        // act
        task.showOn(view);

        // assert
        verify(view).showTask(true, "testing", new ChangeTaskStatusCommand(task));
    }

    @Test
    public void callsMethod_dataBase_string() throws Exception {
        // arrange
        SqlTask task = new SqlTask(123, dataBase);

        // act
        task.showOn(view);

        // assert
        verify(dataBase).string("name", "id = 123");
    }

    @Test
    public void callsMethod_dataBase_bool() throws Exception {
        // arrange
        SqlTask task = new SqlTask(123, dataBase);

        // act
        task.showOn(view);

        // assert
        verify(dataBase).bool("done", "id = 123");
    }
}
