public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(String arguments) throws DukeException {
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