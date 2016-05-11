package com.chmielowski.contexttasklist.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.chmielowski.contexttasklist.Persistence;
import com.chmielowski.contexttasklist.R;
import com.chmielowski.contexttasklist.TaskList;
import com.chmielowski.contexttasklist.commands.ChangeStatusCommand;
import com.chmielowski.contexttasklist.commands.DeleteTaskCommand;
import com.chmielowski.contexttasklist.sql.SqlPersistence;
import com.chmielowski.contexttasklist.sql.SqlTaskList;
import com.chmielowski.contexttasklist.view.dialog.AddTaskDialog;

public final class FragmentListView extends Fragment implements ListView {

    private View view;

    @Nullable
    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState
    ) {
        view = inflater.inflate(R.layout.task_list, container, false);
        Persistence tasksDataBase = new SqlPersistence(
                view.getContext(), "Tasks");
        Persistence listsDataBase = new SqlPersistence(
                view.getContext(), "Lists");
        int taskListId = getArguments().getInt("id", 0);
        final TaskList taskList = new SqlTaskList(taskListId,
                tasksDataBase,
                listsDataBase);
        try {
            taskList.showTasksOn(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final AddTaskDialog dialog = new AddTaskDialog(
                view.getContext(),
                this,
                taskList);
        Button addTaskButton = (Button) view.findViewById(R.id.add_task_button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                dialog.show();
            }
        });
        return view;
    }

    @Override
    public void showTask(final boolean isDone,
                         final String description,
                         final ChangeStatusCommand statusCommand,
                         final DeleteTaskCommand deleteCommand) {
        taskListLayout().addView(
                new CheckboxTaskView(
                        isDone,
                        description,
                        statusCommand,
                        deleteCommand
                ).showOn(this.view.getContext(), this, View.generateViewId())
        );
    }

    private LinearLayout taskListLayout() {
        return (LinearLayout) view.findViewById(R.id.layout_tasks);
    }

    @Override
    public void removeTask(final int id) {
        try {
            taskListLayout().removeView(view.findViewById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clean() {
        taskListLayout().removeAllViews();
    }

}
