package cheryl.command;

import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command: displays all tasks in the TaskList.
     *
     * @param tasks   The TaskList containing all tasks
     * @param ui      The Ui to display the tasks
     * @param storage The Storage (unused)
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.showMessage((i + 1) + "." + tasks.getTask(i));
        }
    }

    public boolean isExit() {
        return false;
    }
}
