import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("Hello! I'm Cheryl");
        System.out.println("What can I do for you?");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
