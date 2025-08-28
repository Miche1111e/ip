public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String arguments) throws DukeException {
        try {
            this.index = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number");
        }
    }

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