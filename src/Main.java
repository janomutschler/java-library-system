import model.Book;
import service.Library;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        preloadBooks(library);

        boolean running = true;
		int choice = -1;

        while (running) {
            printMenu();
			try {
            	choice = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input. Please enter a number.");
				sc.nextLine();
				continue;
			}
            sc.nextLine(); // consume newline

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
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void preloadBooks(Library library) {
        library.addBook(new Book(1, "Dune", "Frank Herbert", "Sci-Fi", true));
        library.addBook(new Book(2, "1984", "George Orwell", "Dystopia", true));
        library.addBook(new Book(3, "Clean Code", "Robert C. Martin", "Programming", true));
        library.addBook(new Book(4, "Dune", "Frank Herbert", "Sci-Fi", true));
        library.addBook(new Book(5, "Animal Farm", "George Orwell", "Satire", false));
    }

    private static void listAllBooks(Library library) {
        System.out.println("\n=== All Books ===");
        for (Book book : library.listAllBooks()) {
            System.out.println(book);
        }
    }

    private static void addBook(Scanner sc, Library library) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter title: ");
        String title = sc.nextLine();

        System.out.print("Enter author: ");
        String author = sc.nextLine();

        System.out.print("Enter genre: ");
        String genre = sc.nextLine();

        System.out.print("Is available (true/false): ");
        boolean available = sc.nextBoolean();
        sc.nextLine();

        Book book = new Book(id, title, author, genre, available);

        boolean added = library.addBook(book);

        if (added) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Error: ID already exists.");
        }
    }

    private static void removeBook(Scanner sc, Library library) {
        System.out.print("Enter book ID to remove: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean removed = library.removeBookById(id);

        if (removed) {
            System.out.println("Book removed.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void searchBooks(Scanner sc, Library library) {
        System.out.print("Enter title keyword: ");
        String keyword = sc.nextLine();

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
        System.out.print("Enter book ID to borrow: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean success = library.borrowBook(id);

        if (success) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Cannot borrow book (not found or already borrowed).");
        }
    }

    private static void returnBook(Scanner sc, Library library) {
        System.out.print("Enter book ID to return: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean success = library.returnBook(id);

        if (success) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Cannot return book (not found or already available).");
        }
    }
}