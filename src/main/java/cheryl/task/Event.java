package cheryl.task;

public class Event extends Task {
    String from;
    String to;

    public Event(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}