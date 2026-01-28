package bmo;

import bmo.commands.*;
import bmo.tasks.ToDo;
import bmo.tasks.Deadline;
import bmo.tasks.Event;

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
            case "mark":
                if (details.isEmpty()) throw new BmoException("Please indicate which task to mark.");
                try {
                    return new MarkCommand(Integer.parseInt(details) - 1);
                } catch (NumberFormatException e) {
                    throw new BmoException("Please provide a valid task number.");
                }
            case "unmark":
                if (details.isEmpty()) throw new BmoException("Please indicate which task to unmark.");
                try {
                    return new UnmarkCommand(Integer.parseInt(details) - 1);
                } catch (NumberFormatException e) {
                    throw new BmoException("Please provide a valid task number.");
                }
            case "delete":
                if (details.isEmpty()) throw new BmoException("Please indicate which task to delete.");
                try {
                    return new DeleteCommand(Integer.parseInt(details) - 1);
                } catch (NumberFormatException e) {
                    throw new BmoException("Please provide a valid task number.");
                }
            case "todo":
                if (details.isEmpty()) throw new BmoException("The description of a todo cannot be empty.");
                return new AddCommand(new ToDo(details));
            case "deadline":
                if (details.isEmpty()) throw new BmoException("The description of a deadline cannot be empty.");
                String[] dParts = details.split(" /by ");
                if (dParts.length < 2) throw new BmoException("Invalid deadline format. Use: deadline <desc> /by <time>");
                return new AddCommand(new Deadline(dParts[0], dParts[1]));
            case "event":
                if (details.isEmpty()) throw new BmoException("The description of an event cannot be empty.");
                String[] eParts = details.split(" /from ");
                if (eParts.length < 2) throw new BmoException("Invalid event format. Use: event <desc> /from <time> /to <time>");
                String[] tParts = eParts[1].split(" /to ");
                if (tParts.length < 2) throw new BmoException("Invalid event format. Missing /to.");
                return new AddCommand(new Event(eParts[0], tParts[0], tParts[1]));
            default:
                throw new BmoException("BMO doesn't understand that command.");
        }
    }
}
