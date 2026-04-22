import service.Library;
import ui.ConsoleApp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        try (Scanner sc = new Scanner(System.in)) {
            ConsoleApp app = new ConsoleApp(library, sc);
            app.run();
        }
    }
}