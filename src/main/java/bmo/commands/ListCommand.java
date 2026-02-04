package bmo.commands;

import bmo.tasks.TaskList;
import bmo.Ui;
import bmo.Storage;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }
}
