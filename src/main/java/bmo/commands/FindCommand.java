package bmo.commands;

import bmo.Storage;
import bmo.Ui;
import bmo.tasks.Task;
import bmo.tasks.TaskList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command to find tasks that contain a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> allTasks = tasks.getTasks();
        List<Task> matchingTasks = allTasks.stream()
                .filter(t -> t.toString().contains(keyword))
                .collect(Collectors.toList());
        ui.showFoundTasks(matchingTasks);
    }
}
