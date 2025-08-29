package cheryl.util;

import java.util.Scanner;

/**
 * Handles user interaction.
 * Responsible for showing messages to the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Creates a new Ui object and initializes the input scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a greeting message when the app starts.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Cheryl");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The trimmed user input string
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Prints an error message to the user.
     *
     * @param message The error message to display
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints a message to the user.
     *
     * @param message The message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }
}