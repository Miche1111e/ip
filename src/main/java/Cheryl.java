import java.util.ArrayList;
import java.util.Scanner;

public class Cheryl {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> inputs = new ArrayList<>();

        System.out.println("Hello! I'm Cheryl");
        System.out.println("What can I do for you?");

        while(true) {
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals("list")){
                for(int i = 0; i < inputs.size(); i++) {
                    System.out.println((i + 1) + "." + inputs.get(i));
                }
            } else {
                inputs.add(cmd);
            }
        }
        sc.close();
    }
}
