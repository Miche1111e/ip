package cheryl.util;

import cheryl.task.Deadline;
import cheryl.task.Event;
import cheryl.task.Task;
import cheryl.task.Todo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Objects;
/**
 * Handles reading from and writing to the file system for task persistence.
 */
public class Storage {

    private String filePath;

    /**
     * Creates a new Storage with the given file path.
     *
     * @param filePath Path to the file where tasks are saved
     */
    public Storage(String filePath) {
        this.filePath = Objects.requireNonNull(filePath, "filePath must not be null");
        assert !this.filePath.isBlank() : "filePath should not be blank";
    }

    /**
     * Loads the list of tasks from the file.
     *
     * @return List of tasks loaded from the file
     * @throws IOException If an I/O error occurs during loading
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        File folder = file.getParentFile();

        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
            return tasks;
        }

        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String title = parts[2];

            switch (type) {
                case "T":
                    Task todo = new Todo(title);
                    if (isDone) todo.mark();
                    tasks.add(todo);
                    break;
                case "D":
                    String by = parts[3];
                    LocalDate dueDate = LocalDate.parse(by);
                    Task deadline = new Deadline(title, dueDate);
                    if (isDone) deadline.mark();
                    tasks.add(deadline);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    Task event = new Event(title, from, to);
                    if (isDone) event.mark();
                    tasks.add(event);
                    break;
                default:
                    break;
            }
        }
        fileScanner.close();
        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks List of tasks to save
     * @throws IOException If an I/O error occurs during saving
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : tasks) {
            String line = "";
            if (t instanceof Todo) {
                line = "T | " + (t.isDone() ? "1" : "0") + " | " + t.getTitle();
            } else if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                line = "D | " + (d.isDone() ? "1" : "0") + " | " + d.getTitle() + " | " + d.getDueDate();
            } else if (t instanceof Event) {
                Event e = (Event) t;
                line = "E | " + (e.isDone() ? "1" : "0") + " | " + e.getTitle() + " | " + e.getFrom() + " | " + e.getTo();
            }
            fw.write(line + "\n");
        }
        fw.close();
    }
}