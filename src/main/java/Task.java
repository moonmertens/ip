public class Task {
    
    private boolean finished;
    private String desc;

    public Task(String desc) {
        this.desc = desc;
        this.finished = false;
    }

    public void setStatus(boolean isDone) {
        this.finished = isDone;
    }

    @Override
    public String toString() {
        if (this.finished) {
            return "[X] " + this.desc; 
        }
        return "[ ] " + this.desc;
    }

}
