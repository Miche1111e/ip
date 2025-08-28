import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    // Constructor: start with an empty list
    public TaskList() {
        tasks = new ArrayList<>();
    }

    // Constructor: start with a pre-existing list (e.g., loaded from file)
    public TaskList(List<Task> initialTasks) {
        tasks = new ArrayList<>(initialTasks);
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    public void markTask(int index) throws IndexOutOfBoundsException {
        tasks.get(index).mark();
    }

    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        tasks.get(index).unmark();
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks); // return a copy to avoid external modification
    }

    public int size() {
        return tasks.size();
    }
}
