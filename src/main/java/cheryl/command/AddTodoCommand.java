package cheryl.command;

import cheryl.task.Task;
import cheryl.task.Todo;
import cheryl.util.DukeException;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

public class AddTodoCommand extends Command {
    private String title;

    public AddTodoCommand(String arguments) throws DukeException {
        if (arguments.trim().isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        this.title = arguments.trim();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Todo(title);
        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(task.toString());
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