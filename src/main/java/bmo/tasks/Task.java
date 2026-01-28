package bmo.tasks;

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

    public String toSaveString() {
        return (this.finished ? "1" : "0") + " | " + this.desc;
    }

    @Override
    public String toString() {
        if (this.finished) {
            return "[X] " + this.desc; 
        }
        return "[ ] " + this.desc;
    }

}
