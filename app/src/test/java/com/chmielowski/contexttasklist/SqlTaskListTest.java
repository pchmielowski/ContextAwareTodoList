package com.chmielowski.contexttasklist;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SqlTaskListTest {
//
//    @Mock
//    TaskListView view;
//
//    @Mock
//    Persistence dataBase;
//
//    @Test
//    public void showOn_emptyDataBase_callsGetTasks() {
//        // arrange
//        when(dataBase.getTasks()).thenReturn(new Iterable<Task>() {
//            @Override
//            public Iterator<Task> iterator() {
//                return new ArrayList<Task>().iterator();
//            }
//        });
//        TaskList taskList = new TaskList(dataBase);
//
//        // act
//        taskList.showOn(view);
//
//        // assert
//        verify(dataBase).getTasks();
//    }
//
//    @Mock
//    Task task;
//
//    @Test
//    public void showOn_dataBaseOneTask_callsShowOn() {
//        // arrange
//        when(dataBase.getTasks()).thenReturn(new Iterable<Task>() {
//            @Override
//            public Iterator<Task> iterator() {
//                ArrayList<Task> list = new ArrayList<>();
//                list.add(task);
//                return list.iterator();
//            }
//        });
//        TaskList taskList = new TaskList(dataBase);
//
//        // act
//        taskList.showOn(view);
//
//        // assert
//        verify(task).showOn(view);
//    }
}