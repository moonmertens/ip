package bmo.tasks;

public class Task {
    
    private boolean isDone;
    private String desc;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String toSaveString() {
        return (this.isDone ? "1" : "0") + " | " + this.desc;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.desc; 
        }
        return "[ ] " + this.desc;
    }

}
