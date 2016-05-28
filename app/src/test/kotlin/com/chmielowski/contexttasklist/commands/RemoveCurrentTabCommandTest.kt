package com.chmielowski.contexttasklist.commands

import com.chmielowski.contexttasklist.view.ListsView
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class RemoveCurrentTabCommandTest {
    @Test
    @Throws(Exception::class)
    fun calls_task_delete() {
        val mockedView = Mockito.mock(ListsView::class.java)
        val command = RemoveCurrentTabCommand(mockedView)

        command.execute()

        verify<ListsView>(mockedView).removeCurrentTab()
    }
}
