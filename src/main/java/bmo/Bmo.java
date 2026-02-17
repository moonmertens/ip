package bmo;

import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

import bmo.commands.Command;
import bmo.tasks.TaskList;

/**
 * The main class for the Bmo application.
 */
public class Bmo {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Constructs a Bmo instance with the specified file path for storage.
     *
     * @param filePath The file path to store task data.
     */
    public Bmo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Error loading data.");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     */
    public void run() {
        ui.showWelcome();
        // AI-assisted: avoid shadowing the instance field for clearer state tracking.
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                isExit = handleCommand(fullCommand);
            } catch (NoSuchElementException e) {
                // End of input, exit gracefully
                isExit = true;
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The user's input string.
     * @return The response string from Bmo.
     */
    public String getResponse(String input) {
        boolean shouldExit = handleCommand(input);
        if (shouldExit) {
            this.isExit = true;
        }
        return ui.getResponse();
    }

    private boolean handleCommand(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return c.isExit();
        } catch (BmoException e) {
            ui.showError(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.showError("Invalid date format. Please use: yyyy-MM-dd HHmm");
        } catch (Exception e) {
            ui.showError("An unexpected error occurred: " + e.getMessage());
        }
        return false;
    }

    /**
     * Returns true if the exit command has been executed.
     *
     * @return True if the user has requested to exit, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Starts the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Bmo("bmo_data.txt").run();
    }
}
