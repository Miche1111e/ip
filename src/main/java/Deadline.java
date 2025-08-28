import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate dueDate;

    Deadline(String title, LocalDate dueDate) {
        super(title);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}