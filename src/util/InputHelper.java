package util;

import java.util.Scanner;

public class InputHelper {

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

    public static String readString(Scanner sc, String message) {
        System.out.print(message + ": ");
        return sc.nextLine();
    }

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