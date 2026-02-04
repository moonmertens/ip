package bmo.commands;

import bmo.BmoException;
import bmo.Storage;
import bmo.Ui;
import bmo.tasks.Task;
import bmo.tasks.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The zero-based index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BmoException {
        if (index < 0 || index >= tasks.size()) {
            throw new BmoException("Task number is out of bounds.");
        }
        Task task = tasks.delete(index);
        storage.save(tasks.getTasks());
        ui.showDeleted(task, tasks.size());
    }
}
