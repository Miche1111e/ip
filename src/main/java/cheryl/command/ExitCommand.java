package cheryl.command;

import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command: prints a farewell message.
     *
     * @param tasks   The TaskList (unused)
     * @param ui      The Ui to display farewell message
     * @param storage The Storage (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Returns true because this command exits the application.
     *
     * @return true
     */
    public boolean isExit() {
        return true;
    }
}
