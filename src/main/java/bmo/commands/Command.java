package bmo.commands;

import bmo.BmoException;
import bmo.Storage;
import bmo.Ui;
import bmo.tasks.TaskList;

/**
 * Represents a command executed by the user.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save/load data.
     * @throws BmoException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BmoException;

    /**
     * Checks if the command triggers an exit of the application.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

