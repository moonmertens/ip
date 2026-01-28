package bmo.commands;

import bmo.tasks.TaskList;
import bmo.Ui;
import bmo.Storage;

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
