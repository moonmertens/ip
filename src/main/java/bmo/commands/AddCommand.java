package bmo.commands;

import bmo.tasks.Task;
import bmo.tasks.TaskList;
import bmo.Ui;
import bmo.Storage;
import bmo.BmoException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showAdded(task, tasks.size());
    }
}
