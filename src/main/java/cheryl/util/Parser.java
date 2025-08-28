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

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

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