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

        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String details = parts.length > 1 ? parts[1] : "";

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

    private static int parseTaskIndex(String details, String emptyMessage) throws BmoException {
        if (details.isEmpty()) {
            throw new BmoException(emptyMessage);
        }
        try {
            return Integer.parseInt(details) - 1;
        } catch (NumberFormatException e) {
            throw new BmoException("Please provide a valid task number.");
        }
    }

    private static Command parseDeadlineCommand(String details) throws BmoException {
        if (details.isEmpty()) {
            throw new BmoException("The description of a deadline cannot be empty.");
        }
        String[] dParts = details.split(" /by ");
        if (dParts.length < 2) {
            throw new BmoException("Invalid deadline format. Use: deadline <desc> /by <time>");
        }
        return new AddCommand(new Deadline(dParts[0], dParts[1]));
    }

    private static Command parseEventCommand(String details) throws BmoException {
        if (details.isEmpty()) {
            throw new BmoException("The description of an event cannot be empty.");
        }
        String[] eParts = details.split(" /from ");
        if (eParts.length < 2) {
            throw new BmoException("Invalid event format. Use: event <desc> /from <time> /to <time>");
        }
        String[] tParts = eParts[1].split(" /to ");
        if (tParts.length < 2) {
            throw new BmoException("Invalid event format. Missing /to.");
        }
        return new AddCommand(new Event(eParts[0], tParts[0], tParts[1]));
    }
}
