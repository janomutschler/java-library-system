package ui;

import io.FileManager;
import model.Book;
import service.Library;
import util.InputHelper;

import java.util.List;
import java.util.Scanner;

/**
 * Console controller that handles user interaction and delegates business logic
 * to the Library and FileManager services.
 */
public class ConsoleApp {

    private final Library library;
    private final Scanner scanner;
    private final FileManager fileManager;

    public ConsoleApp(Library library, Scanner scanner) {
        this.library = library;
        this.scanner = scanner;
        this.fileManager = new FileManager();
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = InputHelper.readInt(scanner, "Choose an option");

            switch (choice) {
                case 1:
                    listAllBooks();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    removeBook();
                    break;
                case 4:
                    searchBooks();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    loadFromFile();
                    break;
                case 8:
                    saveReport();
                    break;
                case 9:
                    undoLastAction();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void printMenu() {
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

    private void loadFromFile() {
        String path = InputHelper.readString(scanner, "Enter file path");
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

    private void saveReport() {
        String path = InputHelper.readString(scanner, "Enter output file path");
        fileManager.saveReport(path, library);
    }

    private void undoLastAction() {
        boolean undone = library.undoLastAction();

        if (undone) {
            System.out.println("Last action undone.");
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    private void listAllBooks() {
        System.out.println("\n=== All Books ===");
        for (Book book : library.listAllBooks()) {
            System.out.println(book);
        }
    }

    private void addBook() {
        int id = InputHelper.readInt(scanner, "Enter ID");
        String title = InputHelper.readString(scanner, "Enter title");
        String author = InputHelper.readString(scanner, "Enter author");
        String genre = InputHelper.readString(scanner, "Enter genre");
        boolean available = InputHelper.readBoolean(scanner, "Is available");

        Book book = new Book(id, title, author, genre, available);
        boolean added = library.addBook(book);

        if (added) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Error: ID already exists.");
        }
    }

    private void removeBook() {
        int id = InputHelper.readInt(scanner, "Enter book ID to remove");
        boolean removed = library.removeBookById(id);

        if (removed) {
            System.out.println("Book removed.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private void searchBooks() {
        String keyword = InputHelper.readString(scanner, "Enter title keyword");
        List<Book> results = library.searchByTitle(keyword);

        if (results.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        System.out.println("\n=== Search Results ===");
        for (Book book : results) {
            System.out.println(book);
        }
    }

    private void borrowBook() {
        int id = InputHelper.readInt(scanner, "Enter book ID to borrow");
        boolean success = library.borrowBook(id);

        if (success) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Cannot borrow book (not found or already borrowed).");
        }
    }

    private void returnBook() {
        int id = InputHelper.readInt(scanner, "Enter book ID to return");
        boolean success = library.returnBook(id);

        if (success) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Cannot return book (not found or already available).");
        }
    }
}
