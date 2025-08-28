public class Task {
    String title;
    boolean isDone;

    Task (String title) {
        this.title = title;
        this.isDone = false;
    }

    void mark() {
        this.isDone = true;
    }
    void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + title;
    }
}