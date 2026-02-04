package bmo.tasks;

/**
 * Represents a todo task without any date attached to it.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param desc The description of the todo task.
     */
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}