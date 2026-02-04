package bmo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toString_validDescription_success() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void setStatus_markAsDone_success() {
        ToDo todo = new ToDo("read book");
        todo.setStatus(true);
        assertEquals("[T][X] read book", todo.toString());
    }
}
