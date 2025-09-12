package cheryl.util;

import cheryl.command.AddDeadlineCommand;
import cheryl.command.AddEventCommand;
import cheryl.command.AddTodoCommand;
import cheryl.command.Command;
import cheryl.command.DeleteCommand;
import cheryl.command.ExitCommand;
import cheryl.command.ListCommand;
import cheryl.command.MarkCommand;
import cheryl.command.UnmarkCommand;

/**
 * Parses user input strings into Command objects.
 * Responsible for determining which command the user wants to execute.
 */

public class Parser {

    /**
     * Parses the full command string into a specific Command object.
     *
     * @param fullCommand The full user input string
     * @return A Command object corresponding to the user input
     * @throws DukeException If the command is unknown or arguments are invalid
     */
    public static Command parse(String fullCommand) throws DukeException {
        assert fullCommand != null : "parse(): fullCommand should not be null";

        String trimmed = fullCommand.trim();
        if (trimmed.isEmpty()) {
            throw new DukeException("OOPS!!! Command cannot be empty.");
        }

        String[] parts = trimmed.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(arguments);
            case "unmark":
                return new UnmarkCommand(arguments);
            case "todo":
                return new AddTodoCommand(arguments);
            case "deadline":
                return new AddDeadlineCommand(arguments);
            case "event":
                return new AddEventCommand(arguments);
            case "delete":
                return new DeleteCommand(arguments);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}