package util;

import java.util.Scanner;

/**
 * Utility methods for reading validated console input.
 */
public class InputHelper {

    private InputHelper() {
        // Utility class
    }

    /**
     * Reads an integer from the console and repeats until a valid number is entered.
     */
    public static int readInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message + ": ");
            String input = sc.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * Reads a raw line of text from the console.
     */
    public static String readString(Scanner sc, String message) {
        System.out.print(message + ": ");
        return sc.nextLine();
    }

    /**
     * Reads a boolean value and accepts true/false or yes/no aliases.
     */
    public static boolean readBoolean(Scanner sc, String message) {
        while (true) {
            System.out.print(message + " (true/false): ");
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("true") || input.equals("yes") || input.equals("t")) {
                return true;
            } else if (input.equals("false") || input.equals("no") || input.equals("f")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter true/false (or yes/no).");
            }
        }
    }
}