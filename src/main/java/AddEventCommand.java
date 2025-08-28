public class AddEventCommand extends Command {
    private String title;
    private String from;
    private String to;

    public AddEventCommand(String arguments) throws DukeException {
        try {
            String[] parts = arguments.split("/from", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            }
            title = parts[0].trim();
            String[] times = parts[1].split("/to", 2);
            if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
                throw new DukeException("OOPS!!! Start and end time of an event cannot be empty.");
            }
            from = times[0].trim();
            to = times[1].trim();
        } catch (Exception e) {
            throw new DukeException("Invalid event format! Use: event <title> /from <start> /to <end>");
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Event(title, from, to);
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