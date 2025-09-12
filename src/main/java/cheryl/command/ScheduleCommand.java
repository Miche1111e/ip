package cheryl.command;

import cheryl.util.DukeException;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;
import cheryl.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ScheduleCommand implements Command {
    private LocalDate date;

    public ScheduleCommand(String dateStr) throws DukeException {
        try {
            this.date = LocalDate.parse(dateStr); // format: yyyy-MM-dd
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Use yyyy-MM-dd.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        List<Task> scheduled = tasks.getTasksForDate(date);
        if (scheduled.isEmpty()) {
            ui.showMessage("No tasks scheduled for " + date);
        } else {
            ui.showMessage("Tasks scheduled for " + date + ":");
            for (Task t : scheduled) {
                ui.showMessage(t.toString());
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
