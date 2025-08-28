package cheryl.task;

public class Task {
    String title;
    boolean isDone;

    Task (String title) {
        this.title = title;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }
    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + title;
    }
}