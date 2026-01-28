import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String desc, String from, String to) {
        super(desc);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    @Override
    public String toSaveString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + super.toSaveString() + " | " + this.from.format(formatter) + " | " + this.to.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
        return "[E]" + super.toString() + " (from: " + this.from.format(displayFormatter) + " to: " + this.to.format(displayFormatter) + ")";
    }

}
