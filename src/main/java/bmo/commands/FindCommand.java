package bmo.commands;

import bmo.Storage;
import bmo.Ui;
import bmo.tasks.Task;
import bmo.tasks.TaskList;
import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private String keyword;

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
