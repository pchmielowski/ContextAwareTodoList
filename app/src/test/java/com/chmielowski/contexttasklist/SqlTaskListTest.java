package com.chmielowski.contexttasklist;


import com.chmielowski.contexttasklist.sql.SqlTaskList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SqlTaskListTest {

    @Mock
    Persistence tasksDataBase;
    @Mock
    Persistence listDataBase;

    @Test
    public void removes_itself_from_base() throws Exception {
        // arrange
        TaskList list = new SqlTaskList(123,
                tasksDataBase,
                listDataBase);

        // act
        list.remove();

        // assert
        verify(listDataBase).delete("id=123");
    }

    @Test
    public void removes_tasks_from_base() throws Exception {
        // arrange
        TaskList list = new SqlTaskList(888,
                tasksDataBase,
                listDataBase);

        // act
        list.remove();

        // assert
        verify(tasksDataBase).delete("list=888");
    }
}
