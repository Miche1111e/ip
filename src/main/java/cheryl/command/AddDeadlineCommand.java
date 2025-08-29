package cheryl.command;

import cheryl.task.Task;
import cheryl.task.Deadline;
import cheryl.util.DukeException;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a Deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String title;
    private LocalDate by;

    /**
     * Creates a new AddDeadlineCommand with the given arguments.
     *
     * @param arguments The deadline arguments in format "title /by yyyy-MM-dd"
     * @throws DukeException If the input format is invalid or date is wrong
     */
    public AddDeadlineCommand(String arguments) throws DukeException {
        try {
            String[] parts = arguments.split("/by", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new DukeException("OOPS!!! The description or date of a deadline cannot be empty.");
            }
            this.title = parts[0].trim();
            String byStr = parts[1].trim();
            this.by = LocalDate.parse(byStr);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Please use yyyy-MM-dd format for deadlines.");
        } catch (Exception e) {
            throw new DukeException("Invalid deadline format! Use: deadline <title> /by <date>");
        }
    }

    /**
     * Executes the command: adds a new Deadline to the TaskList.
     *
     * @param tasks   The TaskList to add the Deadline to
     * @param ui      The Ui to display messages
     * @param storage The Storage to save the updated list
     * @throws DukeException If an error occurs during saving
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Deadline(title, by);
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