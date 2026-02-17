package bmo;

import bmo.commands.AddCommand;
import bmo.commands.Command;
import bmo.commands.DeleteCommand;
import bmo.commands.ExitCommand;
import bmo.commands.FindCommand;
import bmo.commands.HelpCommand;
import bmo.commands.ListCommand;
import bmo.commands.MarkCommand;
import bmo.commands.UnmarkCommand;
import bmo.tasks.Deadline;
import bmo.tasks.Event;
import bmo.tasks.ToDo;

/**
 * Parses user input to create valid Command objects.
 */
public class Parser {

    /**
     * Parses the full command string into a specific Command object.
     *
     * @param fullCommand The full user input string.
     * @return The corresponding Command object.
     * @throws BmoException If the command is invalid or missing required details.
     */
    public static Command parse(String fullCommand) throws BmoException {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new BmoException("Please describe the task.");
        }

        // AI-assisted: normalize input to reduce whitespace edge cases.
        String[] parts = splitCommand(fullCommand.trim());
        String commandWord = parts[0].toLowerCase();
        String details = parts[1];

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        case "find":
            if (details.isEmpty()) {
                throw new BmoException("Please provide a keyword to search for.");
            }
            return new FindCommand(details);
        case "mark":
            return new MarkCommand(parseTaskIndex(details, "Please indicate which task to mark."));
        case "unmark":
            return new UnmarkCommand(parseTaskIndex(details, "Please indicate which task to unmark."));
        case "delete":
            return new DeleteCommand(parseTaskIndex(details, "Please indicate which task to delete."));
        case "todo":
            if (details.isEmpty()) {
                throw new BmoException("The description of a todo cannot be empty.");
            }
            return new AddCommand(new ToDo(details));
        case "deadline":
            return parseDeadlineCommand(details);
        case "event":
            return parseEventCommand(details);
        default:
            throw new BmoException("BMO doesn't understand that command.");
        }
    }

    private static String[] splitCommand(String fullCommand) {
        // AI-assisted: centralize command splitting and trimming.
        String[] parts = fullCommand.split("\\s+", 2);
        String commandWord = parts[0];
        String details = parts.length > 1 ? parts[1].trim() : "";
        return new String[] { commandWord, details };
    }

    private static int parseTaskIndex(String details, String emptyMessage) throws BmoException {
        String trimmedDetails = details.trim();
        if (trimmedDetails.isEmpty()) {
            throw new BmoException(emptyMessage);
        }
        try {
            int index = Integer.parseInt(trimmedDetails) - 1;
            if (index < 0) {
                throw new BmoException("Please provide a valid task number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new BmoException("Please provide a valid task number.");
        }
    }

    private static Command parseDeadlineCommand(String details) throws BmoException {
        if (details.trim().isEmpty()) {
            throw new BmoException("The description of a deadline cannot be empty.");
        }
        // AI-assisted: split once to preserve '/by' in descriptions.
        String[] dParts = details.split(" /by ", 2);
        if (dParts.length < 2) {
            throw new BmoException("Invalid deadline format. Use: deadline <desc> /by <time>");
        }
        String description = dParts[0].trim();
        String by = dParts[1].trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new BmoException("Invalid deadline format. Use: deadline <desc> /by <time>");
        }
        return new AddCommand(new Deadline(description, by));
    }

    private static Command parseEventCommand(String details) throws BmoException {
        if (details.trim().isEmpty()) {
            throw new BmoException("The description of an event cannot be empty.");
        }
        // AI-assisted: split once to preserve '/from' in descriptions.
        String[] eParts = details.split(" /from ", 2);
        if (eParts.length < 2) {
            throw new BmoException("Invalid event format. Use: event <desc> /from <time> /to <time>");
        }
        String[] tParts = eParts[1].split(" /to ", 2);
        if (tParts.length < 2) {
            throw new BmoException("Invalid event format. Missing /to.");
        }
        String description = eParts[0].trim();
        String from = tParts[0].trim();
        String to = tParts[1].trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new BmoException("Invalid event format. Use: event <desc> /from <time> /to <time>");
        }
        return new AddCommand(new Event(description, from, to));
    }
}
