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
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BmoException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showError("Invalid date format. Please use: yyyy-MM-dd HHmm");
            } catch (NoSuchElementException e) {
                // End of input, exit gracefully
                isExit = true;
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
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
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                this.isExit = true;
            }
        } catch (BmoException e) {
            ui.showError(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.showError("Invalid date format. Please use: yyyy-MM-dd HHmm");
        } catch (Exception e) {
            ui.showError("An unexpected error occurred: " + e.getMessage());
        }
        return ui.getResponse();
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
