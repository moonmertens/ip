package bmo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bmo.tasks.Deadline;
import bmo.tasks.Event;
import bmo.tasks.Task;
import bmo.tasks.ToDo;

/**
 * Handles loading and saving of tasks to a file on the hard disk.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     */
    public List<Task> load() {
        List<Task> loadedTasks = new ArrayList<>();
        try {
            File f = new File(this.filePath);
            if (!f.exists()) {
                return loadedTasks;
            }
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                Task task = null;
                switch (parts[0]) {
                case "T":
                    task = new ToDo(parts[2]);
                    break;
                case "D":
                    task = new Deadline(parts[2], parts[3]);
                    break;
                case "E":
                    task = new Event(parts[2], parts[3], parts[4]);
                    break;
                default:
                    // Ignore invalid tasks
                    break;
                }
                if (task != null) {
                    if (parts[1].equals("1")) {
                        task.setStatus(true);
                    }
                    loadedTasks.add(task);
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return loadedTasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to save.
     */
    public void save(List<Task> tasks) {
        try {
            File f = new File(this.filePath);
            if (f.getParentFile() != null) {
                f.getParentFile().mkdirs();
            }
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : tasks) {
                fw.write(task.toSaveString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
