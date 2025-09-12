package cheryl.command;

import cheryl.util.TaskList;
import cheryl.util.Ui;
import cheryl.util.Storage;
import cheryl.util.DukeException;
import cheryl.task.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to find tasks containing a keyword.
 */
public class FindCommand implements Command {
    private String keyword;

    /**
     * Creates a FindCommand with the given keyword.
     *
     * @param arguments The keyword to search for
     * @throws DukeException If the keyword is empty
     */
    public FindCommand(String arguments) throws DukeException {
        if (arguments.trim().isEmpty()) {
            throw new DukeException("OOPS!!! The search keyword cannot be empty.");
        }
        this.keyword = arguments.trim();
    }

    /**
     * Executes the command: lists all tasks containing the keyword.
     *
     * @param tasks   The TaskList to search
     * @param ui      The Ui to display results
     * @param storage The Storage (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getTitle().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        ui.showLine();
        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found.");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + "." + matchingTasks.get(i));
            }
        }
        ui.showLine();
    }

    public boolean isExit() {
        return false;
    }
}
