import java.util.ArrayList;
import java.util.Scanner;

class Task {
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

    public String getTypeIcon() {
        return "[ ]";
    }
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + title;
    }
}

class Todo extends Task {
    Todo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    String dueDate;

    Deadline(String title, String dueDate) {
        super(title);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}

class Event extends Task {
    String from;
    String to;

    Event(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}


public class Cheryl {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Cheryl");
        System.out.println("What can I do for you?");

        while(true) {
            String cmd = sc.nextLine().trim();
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals("list")){
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
            } else if (cmd.startsWith("mark ")){
                try {
                    int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
                    tasks.get(index).mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index));
                } catch (Exception e) {
                    System.out.println("Invalid task number");
                }
            } else if (cmd.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
                    tasks.get(index).unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(index));
                } catch (Exception e) {
                    System.out.println("Invalid task number");
                }
            } else if (cmd.startsWith("todo ")) {
                String title = cmd.length() > 4 ? cmd.substring(5).trim() : "";
                if (title.isEmpty()) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    tasks.add(new Todo(title));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size()-1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
            } else if (cmd.startsWith("deadline ")) {
                try {
                    String[] parts = cmd.substring(9).split("/by", 2);
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        System.out.println("OOPS!!! The description or date of a deadline cannot be empty.");
                    } else {
                        String title = parts[0].trim();
                        String by = parts[1].trim();
                        tasks.add(new Deadline(title, by));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid deadline format! Use: deadline <title> /by <date>");
                }
            } else if (cmd.startsWith("event ")) {
                try {
                    String[] parts = cmd.substring(6).split("/from", 2);
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                        System.out.println("OOPS!!! The description of an event cannot be empty.");
                    } else {
                        String title = parts[0].trim();
                        String[] times = parts[1].split("/to", 2);
                        if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
                            System.out.println("OOPS!!! Start and end time of an event cannot be empty.");
                        } else {
                            String from = times[0].trim();
                            String to = times[1].trim();
                            tasks.add(new Event(title, from, to));
                            System.out.println("Got it. I've added this task:");
                            System.out.println(tasks.get(tasks.size()-1));
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Invalid event format! Use: event <title> /from <start> /to <end>");
                }
            } else {
                System.out.println("Invalid command");
            }
        }
        sc.close();
    }
}
