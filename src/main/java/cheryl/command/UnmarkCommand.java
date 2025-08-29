package cheryl.command;

import cheryl.util.DukeException;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates a new UnmarkCommand.
     *
     * @param arguments The index of the task to mark
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException("Invalid task number");
        }
        tasks.unmarkTask(index);
        ui.showMessage("OK, I've marked this task as not done yet:");
        ui.showMessage(tasks.getTask(index).toString());
        try {
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            throw new DukeException("Error saving tasks: " + e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}