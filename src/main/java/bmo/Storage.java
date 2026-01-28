package bmo;

import bmo.tasks.Task;
import bmo.tasks.ToDo;
import bmo.tasks.Deadline;
import bmo.tasks.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
