package bmo.commands;

import bmo.tasks.TaskList;
import bmo.Ui;
import bmo.Storage;
import bmo.BmoException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BmoException {
        if (index < 0 || index >= tasks.size()) {
            throw new BmoException("Task number is out of bounds.");
        }
        tasks.get(index).setStatus(true);
        storage.save(tasks.getTasks());
        ui.showMarked(tasks.get(index));
    }
}
