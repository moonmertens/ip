package bmo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param desc The description of the task.
     * @param by The deadline date and time.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() + " | " + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

}
