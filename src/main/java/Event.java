public class Event extends Task {
    
    private String from;
    private String to;

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String finalString = "[E]";
        finalString += " " + super.toString();
        finalString += " (from: " + this.from;
        finalString += " to: " + this.to + ")";
        return finalString;
    }

}
