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
    public boolean isDone() { return isDone; }
    public String getTitle() { return title; }


    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + title;
    }
}