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

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + title;
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
            } else {
                tasks.add(new Task(cmd));
            }
        }
        sc.close();
    }
}
