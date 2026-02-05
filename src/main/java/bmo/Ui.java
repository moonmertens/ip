package bmo;

import java.util.List;
import java.util.Scanner;

import bmo.tasks.Task;

/**
 * Handles all user interaction, including reading input and printing messages.
 */
public class Ui {
    private static final String NAME = "BMO";
    private Scanner sc;
    private StringBuilder responseBuffer;

    /**
     * Constructs a new Ui instance and initializes the scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
        this.responseBuffer = new StringBuilder();
    }

    /**
     * Prints the given messages to the user.
     *
     * @param messages The messages to be displayed.
     */
    public void println(String... messages) {
        for (String message : messages) {
            System.out.println(message);
            responseBuffer.append(message).append("\n");
        }
    }

    /**
     * Returns the accumulated response and clears the buffer.
     *
     * @return The response string.
     */
    public String getResponse() {
        String response = responseBuffer.toString();
        responseBuffer.setLength(0);
        return response;
    }

    /**
     * Reads a command line from the user.
     *
     * @return The line of text entered by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        println("Hello! I'm " + NAME, "What can I do for you?");
    }

    /**
     * Displays the exit message to the user.
     */
    public void showBye() {
        println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        println("OOPS!!! " + message);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(List<Task> tasks) {
        println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays a message confirming the addition of a task.
     *
     * @param task The task that was added.
     * @param size The new size of the task list.
     */
    public void showAdded(Task task, int size) {
        println("Got it. I've added this task:", "  " + task, "Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message confirming the deletion of a task.
     *
     * @param task The task that was deleted.
     * @param size The new size of the task list.
     */
    public void showDeleted(Task task, int size) {
        println("Noted. I've removed this task:", "  " + task, "Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message confirming a task has been marked as done.
     *
     * @param task The task that was marked.
     */
    public void showMarked(Task task) {
        println("Nice! I've marked this task as done:", task.toString());
    }

    /**
     * Displays a message confirming a task has been marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarked(Task task) {
        println("Ok, I've marked this task as not done yet:", task.toString());
    }

    /**
     * Displays the list of tasks matching a search keyword.
     *
     * @param tasks The list of matching tasks.
     */
    public void showFoundTasks(List<Task> tasks) {
        println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            println((i + 1) + ". " + tasks.get(i));
        }
    }
}
