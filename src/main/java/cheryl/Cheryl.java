package cheryl;

import cheryl.command.Command;
import cheryl.util.DukeException;
import cheryl.util.Parser;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;

/**
 * Main class of the Cheryl application.
 * Handles initialization of storage, tasks, and UI, and runs the main command loop.
 */
public class Cheryl {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Cheryl application instance.
     * Initializes UI, loads tasks from storage, and handles corrupted files.
     *
     * @param filePath Path to the file used for task storage.
     */
    public Cheryl(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Warning: could not load tasks (file may be corrupted).");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main command loop of the application.
     * Continuously reads user input, parses and executes commands, until exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main entry point of the Cheryl application.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        new Cheryl("./data/task.txt").run();
    }
}
