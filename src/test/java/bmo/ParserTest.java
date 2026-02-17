package bmo;

import bmo.commands.AddCommand;
import bmo.commands.ExitCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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

    @Test
    public void parse_todoWithExtraWhitespace_success() throws Exception {
        // AI-assisted: verifies whitespace-tolerant command parsing.
        assertTrue(Parser.parse("   todo    read book   ") instanceof AddCommand);
    }

    @Test
    public void parse_markZero_exceptionThrown() {
        // AI-assisted: verifies index validation for common user mistake.
        try {
            Parser.parse("mark 0");
            fail();
        } catch (BmoException e) {
            assertEquals("Please provide a valid task number.", e.getMessage());
        }
    }
}
