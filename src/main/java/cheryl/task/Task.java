package cheryl.task;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    String title;
    boolean isDone;

    /**
     * Creates a new Task with the given title.
     *
     * @param title The description/title of the task
     */
     public Task (String title) {
        this.title = title;
        assert !this.title.isBlank() : "Task title should not be blank";
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;

        assert this.isDone : "mark() must set isDone to true";
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isDone = false;

        assert !this.isDone : "unmark() must set isDone to false";
    }

    /**
     * Returns whether the task is done.
     *
     * @return true if done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the title of the task.
     *
     * @return the task title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String in the format "[X] title" or "[ ] title"
     */
    @Override
    public String toString() {
        assert title != null : "title must not be null";
        return "[" + (isDone ? "X" : " ") + "] " + title;
    }
}