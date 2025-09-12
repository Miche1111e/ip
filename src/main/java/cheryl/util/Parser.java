package cheryl.util;

import cheryl.command.*;

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
        assert fullCommand != null : "fullCommand should not be null";
        String[] parts = fullCommand.split(" ", 2);

        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";
        assert !commandWord.isEmpty() : "commandWord should not be empty";

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
            // add this inside switch(commandWord) in Parser
            case "schedule":
                return new ScheduleCommand(arguments);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}