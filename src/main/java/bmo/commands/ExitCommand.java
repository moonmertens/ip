package bmo.commands;

import bmo.Storage;
import bmo.Ui;
import bmo.tasks.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
