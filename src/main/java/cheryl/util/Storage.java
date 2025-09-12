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

/**
 * Handles reading from and writing to the file system for task persistence.
 */
public class Storage {

    private String filePath;
    private static final String DELIMITER = " \\| ";
    private static final int IDX_TYPE = 0;
    private static final int IDX_ISDONE = 1;
    private static final int IDX_TITLE = 2;
    private static final int IDX_BY = 3;
    private static final int IDX_FROM = 3;
    private static final int IDX_TO = 4;

    /**
     * Creates a new Storage with the given file path.
     *
     * @param filePath Path to the file where tasks are saved
     */
    public Storage(String filePath) {
        this.filePath = filePath;
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
        ensureFileExists(file);

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                tasks.add(parseLine(line));
            }
        }
        return tasks;
    }

    private void ensureFileExists(File file) throws IOException {
        File folder = file.getParentFile();
        if (!folder.exists()) folder.mkdirs();
        if (!file.exists()) file.createNewFile();
    }

    private Task parseLine(String line) {
        String[] parts = line.split(DELIMITER);
        String type = parts[IDX_TYPE];
        boolean isDone = parts[IDX_ISDONE].equals("1");
        String title = parts[IDX_TITLE];

        Task task;
        switch (type) {
            case "T":
                task = new Todo(title);
                break;
            case "D":
                task = new Deadline(title, LocalDate.parse(parts[IDX_BY]));
                break;
            case "E":
                task = new Event(title, parts[IDX_FROM], parts[IDX_TO]);
                break;
            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
        }
        if (isDone) task.mark();
        return task;
    }


    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks List of tasks to save
     * @throws IOException If an I/O error occurs during saving
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task t : tasks) {
                fw.write(taskToLine(t) + "\n");
            }
        }
    }

    private String taskToLine(Task t) {
        if (t instanceof Todo) {
            return String.format("T | %s | %s", t.isDone() ? "1" : "0", t.getTitle());
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return String.format("D | %s | %s | %s", d.isDone() ? "1" : "0", d.getTitle(), d.getDueDate());
        } else if (t instanceof Event) {
            Event e = (Event) t;
            return String.format("E | %s | %s | %s | %s", e.isDone() ? "1" : "0", e.getTitle(), e.getFrom(), e.getTo());
        }
        throw new IllegalArgumentException("Unknown task type: " + t.getClass());
    }
}