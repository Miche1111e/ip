package cheryl.command;

import cheryl.util.DukeException;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

import java.io.IOException;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand implements Command {
    private int index;

    /**
     * Creates a new UnmarkCommand.
     *
     * @param arguments The index of the task to mark
     * @throws DukeException If the argument cannot be parsed as an integer
     */
    public UnmarkCommand(String arguments) throws DukeException {
        try {
            this.index = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number");
        }
    }

    /**
     * Executes the command: unmarks the task at the given index as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        validateIndex(index, tasks.getSize());
        tasks.unmarkTask(index);
        ui.showTaskStatusChanged(tasks.getTask(index), false);
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new DukeException("Error saving tasks: " + e.getMessage());
        }
    }


    public boolean isExit() {
        return false;
    }

    private int parseIndex(String arguments) throws DukeException {
        try {
            return Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number: " + arguments);
        }
    }

    private void validateIndex(int idx, int size) throws DukeException {
        if (idx < 0 || idx >= size) {
            throw new DukeException("Task number out of range: " + (idx + 1));
        }
    }
}