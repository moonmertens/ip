package bmo.commands;

import bmo.Storage;
import bmo.Ui;
import bmo.tasks.TaskList;

/**
 * Represents a command to show help information to the user.
 */
public class HelpCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
