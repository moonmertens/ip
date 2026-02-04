package bmo.commands;

import bmo.BmoException;
import bmo.Storage;
import bmo.Ui;
import bmo.tasks.TaskList;

/**
 * Represents a command to unmark a task (mark as not done).
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The zero-based index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BmoException {
        if (index < 0 || index >= tasks.size()) {
            throw new BmoException("Task number is out of bounds.");
        }
        tasks.get(index).setStatus(false);
        storage.save(tasks.getTasks());
        ui.showUnmarked(tasks.get(index));
    }
}
