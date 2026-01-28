import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bmo {

    private static final String LINE = "________________________________________________________________";
    private static final String NAME = "BMO";

    private static final String FILEPATH = "bmo_data.txt";
    private static List<Task> storage = new ArrayList<Task>();

    public static void main(String[] args) {
        
        System.out.println(LINE);
        Bmo.greet();
        Bmo.load();

        Scanner sc = new Scanner(System.in);
        
        while (true) {

            // Get user input
            String input = sc.nextLine();
            System.out.println(LINE);

            // Exit condition
            if (input.equals("bye")) {
                Bmo.exit();
                break;
            }
            
            // Else, handle it with process
            Bmo.process(input);

        }

        sc.close();
    }

    private static void process(String input) {
        try {
            if (input == null || input.trim().isEmpty()) {
                throw new BmoException("Please describe the task.");
            }

            String[] inputParts = input.split(" ", 2);
            String commandStr = inputParts[0];
            String details = inputParts.length > 1 ? inputParts[1] : "";

            Command command;
            try {
                command = Command.valueOf(commandStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new BmoException("BMO doesn't understand that command.");
            }

            switch (command) {
                case MARK:
                    if (details.isEmpty()) {
                        throw new BmoException("Please indicate which task to mark.");
                    }
                    Bmo.mark(Integer.parseInt(details) - 1);
                    break;

                case UNMARK:
                    if (details.isEmpty()) {
                        throw new BmoException("Please indicate which task to unmark.");
                    }
                    Bmo.unmark(Integer.parseInt(details) - 1);
                    break;

                case DELETE:
                    if (details.isEmpty()) {
                        throw new BmoException("Please indicate which task to delete.");
                    }
                    Bmo.delete(Integer.parseInt(details) - 1);
                    break;

                case LIST:
                    Bmo.list();
                    break;
                
                case TODO:
                case DEADLINE:
                case EVENT:
                    Bmo.add(command, details);
                    break;

                default:
                    throw new BmoException("BMO doesn't understand that command.");
            }
        } catch (BmoException e) {
            System.out.println("OOPS!!! " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please provide a valid task number.");
        } catch (DateTimeParseException e) {
            System.out.println("OOPS!!! Invalid date format. Please use: yyyy-MM-dd HHmm");
        }
        System.out.println(Bmo.LINE);
    }

    private static void greet() {
        System.out.println("Hello! I'm " + Bmo.NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Bmo.storage.size(); i++) {
            System.out.println((i + 1) + ". " + Bmo.storage.get(i));
        }
    }

    private static void add(Command command, String details) throws BmoException {
        if (details.isEmpty()) {
             throw new BmoException("The description of a " + command.toString().toLowerCase() + " cannot be empty.");
        }

        Task newTask = null;

        switch (command) {
            case TODO:
                newTask = new ToDo(details);
                break;
            
            case DEADLINE:
                String[] dParts = details.split(" /by ");
                if (dParts.length < 2) {
                    throw new BmoException("Invalid deadline format. Use: deadline <desc> /by <time>");
                }
                newTask = new Deadline(dParts[0], dParts[1]);
                break;

            case EVENT:
                String[] eParts = details.split(" /from ");
                if (eParts.length < 2) {
                     throw new BmoException("Invalid event format. Use: event <desc> /from <time> /to <time>");
                }
                String desc = eParts[0];
                String[] tParts = eParts[1].split(" /to ");
                if (tParts.length < 2) {
                     throw new BmoException("Invalid event format. Missing /to.");
                }
                newTask = new Event(desc, tParts[0], tParts[1]);        
                break;
        
            default:
                break;
        }

        if (newTask != null) {
            Bmo.storage.add(newTask);
            Bmo.save();
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + newTask);
            System.out.println("Now you have " + Bmo.storage.size() + " tasks in the list.");
        }
    }

    private static void mark(int index) throws BmoException {
        if (index < 0 || index >= Bmo.storage.size()) {
            throw new BmoException("Task number is out of bounds.");
        }
        Bmo.storage.get(index).setStatus(true);
        Bmo.save();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(Bmo.storage.get(index));
    }

    private static void unmark(int index) throws BmoException {
        if (index < 0 || index >= Bmo.storage.size()) {
            throw new BmoException("Task number is out of bounds.");
        }
        Bmo.storage.get(index).setStatus(false);
        Bmo.save();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(Bmo.storage.get(index));
    }

    private static void delete(int index) throws BmoException {
        if (index < 0 || index >= Bmo.storage.size()) {
            throw new BmoException("Task number is out of bounds.");
        }
        Task task = Bmo.storage.remove(index);
        Bmo.save();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + Bmo.storage.size() + " tasks in the list.");
    }

    private static void save() {
        try {
            File f = new File(Bmo.FILEPATH);
            if (f.getParentFile() != null) {
                f.getParentFile().mkdirs();
            }
            FileWriter fw = new FileWriter(Bmo.FILEPATH);
            for (Task task : Bmo.storage) {
                fw.write(task.toSaveString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
    
    private static void load() {
        try {
            File f = new File(Bmo.FILEPATH);
            if (!f.exists()) {
                return;
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
                    Bmo.storage.add(task);
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

}
