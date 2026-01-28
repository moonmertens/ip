package bmo.tasks;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    
    private boolean finished;
    private String desc;

    /**
     * Constructs a Task with the given description.
     *
     * @param desc The description of the task.
     */
    public Task(String desc) {
        this.desc = desc;
        this.finished = false;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone True if the task is done, false otherwise.
     */
    public void setStatus(boolean isDone) {
        this.finished = isDone;
    }

    /**
     * Returns the string representation of the task for file storage.
     *
     * @return A formatted string suitable for saving to a file.
     */
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
