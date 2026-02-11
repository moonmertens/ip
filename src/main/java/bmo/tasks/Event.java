package bmo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event task with the specified description and time range.
     *
     * @param desc The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
    }

    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + this.from.format(INPUT_FORMAT)
            + " | " + this.to.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DISPLAY_FORMAT)
            + " to: " + this.to.format(DISPLAY_FORMAT) + ")";
    }

}
