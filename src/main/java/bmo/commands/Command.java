package bmo.commands;

import bmo.tasks.TaskList;
import bmo.Ui;
import bmo.Storage;
import bmo.BmoException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BmoException;

    public boolean isExit() {
        return false;
    }
}

