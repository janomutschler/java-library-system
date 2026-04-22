import model.Book;
import service.Library;
import util.InputHelper;

import java.util.List;
import java.util.Scanner;

import io.FileManager;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        boolean running = true;

        while (running) {
            printMenu();

            int choice = InputHelper.readInt(sc, "Choose an option");

            switch (choice) {
                case 1:
                    listAllBooks(library);
                    break;
                case 2:
                    addBook(sc, library);
                    break;
                case 3:
                    removeBook(sc, library);
                    break;
                case 4:
                    searchBooks(sc, library);
                    break;
                case 5:
                    borrowBook(sc, library);
                    break;
                case 6:
                    returnBook(sc, library);
                    break;
                case 7:
                    loadFromFile(sc, library);
                    break;
                case 8:
                    saveReport(sc, library);
                    break;
                case 9:
                    undoLastAction(library);
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Library Menu ===");
        System.out.println("1. List all books");
        System.out.println("2. Add book");
        System.out.println("3. Remove book by ID");
        System.out.println("4. Search by title");
        System.out.println("5. Borrow book");
        System.out.println("6. Return book");
        System.out.println("7. Load books from file");
        System.out.println("8. Save report");
        System.out.println("9. Undo last action");
        System.out.println("0. Exit");
    }

    private static void loadFromFile(Scanner sc, Library library) {
        String path = InputHelper.readString(sc, "Enter file path");

        FileManager fileManager = new FileManager();
        List<Book> books = fileManager.loadBooksFromFile(path);

        int added = 0;
        int skipped = 0;

        for (Book book : books) {
            if (library.addBook(book)) {
                added++;
            } else {
                System.out.println("Skipped duplicate ID: " + book.getId());
                skipped++;
            }
        }

        System.out.println("Loaded books: " + added);
        System.out.println("Skipped (duplicate IDs): " + skipped);
    }

    private static void saveReport(Scanner sc, Library library) {
        String path = InputHelper.readString(sc, "Enter output file path");

        FileManager fileManager = new FileManager();
        fileManager.saveReport(path, library);
    }

    private static void undoLastAction(Library library) {
        boolean undone = library.undoLastAction();

        if (undone) {
            System.out.println("Last action undone.");
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    private static void listAllBooks(Library library) {
        System.out.println("\n=== All Books ===");
        for (Book book : library.listAllBooks()) {
            System.out.println(book);
        }
    }

    private static void addBook(Scanner sc, Library library) {
        int id = InputHelper.readInt(sc, "Enter ID");
        String title = InputHelper.readString(sc, "Enter title");
        String author = InputHelper.readString(sc, "Enter author");
        String genre = InputHelper.readString(sc, "Enter genre");
        boolean available = InputHelper.readBoolean(sc, "Is available");

        Book book = new Book(id, title, author, genre, available);

        boolean added = library.addBook(book);

        if (added) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Error: ID already exists.");
        }
    }

    private static void removeBook(Scanner sc, Library library) {
        int id = InputHelper.readInt(sc, "Enter book ID to remove");

        boolean removed = library.removeBookById(id);

        if (removed) {
            System.out.println("Book removed.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void searchBooks(Scanner sc, Library library) {
        String keyword = InputHelper.readString(sc, "Enter title keyword");

        List<Book> results = library.searchByTitle(keyword);

        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("\n=== Search Results ===");
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }

    private static void borrowBook(Scanner sc, Library library) {
        int id = InputHelper.readInt(sc, "Enter book ID to borrow");

        boolean success = library.borrowBook(id);

        if (success) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Cannot borrow book (not found or already borrowed).");
        }
    }

    private static void returnBook(Scanner sc, Library library) {
        int id = InputHelper.readInt(sc, "Enter book ID to return");

        boolean success = library.returnBook(id);

        if (success) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Cannot return book (not found or already available).");
        }
    }
}