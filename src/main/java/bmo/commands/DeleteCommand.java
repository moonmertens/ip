package bmo.commands;

import bmo.tasks.Task;
import bmo.tasks.TaskList;
import bmo.Ui;
import bmo.Storage;
import bmo.BmoException;

public class DeleteCommand extends Command {
    private int index;

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
