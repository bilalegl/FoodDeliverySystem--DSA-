package utils;

import java.util.Scanner;

public class CLIHelper {

    private static Scanner sc = new Scanner(System.in);

    // Prompt integer input with message
    public static int promptInt(String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Enter a number.");
            sc.next(); // discard invalid input
            System.out.print(message);
        }
        int value = sc.nextInt();
        sc.nextLine(); // consume newline
        return value;
    }

    // Prompt double input
    public static double promptDouble(String message) {
        System.out.print(message);
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid input. Enter a number.");
            sc.next();
            System.out.print(message);
        }
        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }

    // Prompt string input
    public static String promptString(String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    // Prompt yes/no (returns boolean)
    public static boolean promptYesNo(String message) {
        System.out.print(message + " (y/n): ");
        String input = sc.nextLine().trim().toLowerCase();
        while (!input.equals("y") && !input.equals("n")) {
            System.out.print("Invalid input. " + message + " (y/n): ");
            input = sc.nextLine().trim().toLowerCase();
        }
        return input.equals("y");
    }

    // Print a formatted section header
    public static void printSection(String title) {
        System.out.println("\n=== " + title + " ===");
    }

    // Pause CLI until user presses enter
    public static void pause() {
        System.out.println("\nPress Enter to continue...");
        sc.nextLine();
    }
}
