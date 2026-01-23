import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bmo {

    private static final String LINE = "________________________________________________________________";
    private static final String NAME = "BMO";

    private static List<Task> storage = new ArrayList<Task>();

    public static void main(String[] args) {
        
        System.out.println(LINE);
        Bmo.greet();

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

        String[] inputParts = input.split(" ");

        switch (inputParts[0]) {

            case "mark":
                Bmo.mark(Integer.parseInt(inputParts[1]) - 1);
                break;

            case "unmark":
                Bmo.unmark(Integer.parseInt(inputParts[1]) - 1);
                break;

            case "list":
                Bmo.list();
                break;
        
            default:
                Bmo.add(input);
                break;
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

    private static void add(String input) {
        Bmo.storage.add(new Task(input));
        System.out.println("added: " + input);
    }

    private static void mark(int index) {
        Bmo.storage.get(index).toggle();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(Bmo.storage.get(index));
    }

    private static void unmark(int index) {
        Bmo.storage.get(index).toggle();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(Bmo.storage.get(index));
    }

}
