package bmo.commands;

import bmo.Storage;
import bmo.Ui;
import bmo.tasks.TaskList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }
}
