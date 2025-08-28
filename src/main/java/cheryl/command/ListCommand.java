package cheryl.command;

import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

public class ListCommand extends Command {
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