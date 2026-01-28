package bmo;

import bmo.tasks.Task;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "________________________________________________________________";
    private static final String NAME = "BMO";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    public void showTaskList(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void showAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmarked(Task task) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void showFoundTasks(List<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
