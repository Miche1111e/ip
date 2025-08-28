public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}