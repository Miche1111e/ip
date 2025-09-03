package cheryl.command;

import cheryl.util.DukeException;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

/**
 * Represents a command in the Duke application.
 * All commands must implement the execute() method.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The TaskList to operate on
     * @param ui      The Ui object for displaying messages
     * @param storage The Storage object for saving tasks
     * @throws DukeException If an error occurs during execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether this command should exit the application.
     *
     * @return true if this command exits Duke, false otherwise
     */
    public abstract boolean isExit();
}
