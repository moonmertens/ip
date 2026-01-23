public class Task {
    
    private boolean finished;
    private String desc;

    public Task(String desc) {
        this.desc = desc;
        this.finished = false;
    }

    public boolean toggle() {
        this.finished = !this.finished;
        return this.finished;
    }

    @Override
    public String toString() {
        if (this.finished) {
            return "[X] " + this.desc; 
        }
        return "[ ] " + this.desc;
    }

}
