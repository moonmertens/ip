public class Deadline extends Task {
    
    private String deadline;

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String finalString = "[D]";
        finalString += " " + super.toString();
        finalString += " (by: " + this.deadline + ")";
        return finalString;
    }

}
