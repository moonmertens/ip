package bmo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import bmo.commands.AddCommand;
import bmo.commands.ExitCommand;

public class ParserTest {
    @Test
    public void parse_byeCommand_success() throws Exception {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parse_todoCommand_success() throws Exception {
        assertTrue(Parser.parse("todo read book") instanceof AddCommand);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            Parser.parse("blah");
            fail(); // Should not reach here
        } catch (BmoException e) {
            assertEquals("BMO doesn't understand that command.", e.getMessage());
        }
    }
}
