package com.chmielowski.contexttasklist.commands;

import com.chmielowski.contexttasklist.Task;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class DeleteTaskCommandTest {

    @Test
    public void calls_task_delete() throws Exception {
        Task mockedTask = Mockito.mock(Task.class);
        DeleteTaskCommand command = new DeleteTaskCommand(mockedTask);

        command.execute();

        verify(mockedTask).delete();
    }
}
