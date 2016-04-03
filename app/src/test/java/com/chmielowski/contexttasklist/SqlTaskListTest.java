package com.chmielowski.contexttasklist;

//
//@RunWith(MockitoJUnitRunner.class)
//public class SqlTaskListTest {
////
////    @Mock
////    TaskListView view;
////
////    @Mock
////    Persistence dataBase;
////
////    @Test
////    public void showOn_emptyDataBase_callsGetTasks() {
////        // arrange
////        when(dataBase.getTasks()).thenReturn(new Iterable<Task>() {
////            @Override
////            public Iterator<Task> iterator() {
////                return new ArrayList<Task>().iterator();
////            }
////        });
////        SqlTaskList taskList = new SqlTaskList(dataBase);
////
////        // act
////        taskList.showOn(view);
////
////        // assert
////        verify(dataBase).getTasks();
////    }
////
////    @Mock
////    Task task;
////
////    @Test
////    public void showOn_dataBaseOneTask_callsShowOn() {
////        // arrange
////        when(dataBase.getTasks()).thenReturn(new Iterable<Task>() {
////            @Override
////            public Iterator<Task> iterator() {
////                ArrayList<Task> list = new ArrayList<>();
////                list.add(task);
////                return list.iterator();
////            }
////        });
////        SqlTaskList taskList = new SqlTaskList(dataBase);
////
////        // act
////        taskList.showOn(view);
////
////        // assert
////        verify(task).showOn(view);
////    }
//}
