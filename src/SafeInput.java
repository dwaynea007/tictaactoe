import java.util.Scanner;

public class SafeInput {

    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int input = -1;
        while (input < low || input > high) {
            System.out.print(prompt);
            if (console.hasNextInt()) {
                input = console.nextInt();
                if (input < low || input > high) {
                    System.out.println("Input must be between " + low + " and " + high + ".");
                }
            } else {
                System.out.println("Please enter a valid integer.");
                console.next(); // Clear invalid input
            }
        }
        return input;
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = console.next();
            if (input.equalsIgnoreCase("Y")) {
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("Please enter 'Y' or 'N'.");
            }
        }
    }
}
