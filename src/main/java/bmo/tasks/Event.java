package bmo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    @Override
    public String toSaveString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + super.toSaveString() + " | " + this.from.format(formatter)
                + " | " + this.to.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
        return "[E]" + super.toString() + " (from: " + this.from.format(displayFormatter)
                + " to: " + this.to.format(displayFormatter) + ")";
    }

}
