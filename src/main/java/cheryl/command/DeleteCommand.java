package cheryl.command;

import cheryl.task.Task;
import cheryl.util.DukeException;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a new DeleteCommand.
     *
     * @param arguments The index of the task to delete (as a String)
     * @throws DukeException If the index is not a valid number
     */
    public DeleteCommand(String arguments) throws DukeException {
        try {
            this.index = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number");
        }
    }

    /**
     * Executes the command: deletes the task at the given index.
     *
     * @param tasks   The TaskList from which the task will be removed
     * @param ui      The Ui to display messages
     * @param storage The Storage to save the updated list
     * @throws DukeException If an error occurs while saving
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException("Invalid task number");
        }
        Task removed = tasks.deleteTask(index);
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage(removed.toString());
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
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