import model.Book;
import service.Library;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();

        // Add books
        library.addBook(new Book(1, "Dune", "Frank Herbert", "Sci-Fi", true));
        library.addBook(new Book(2, "1984", "George Orwell", "Dystopia", true));
        library.addBook(new Book(3, "Clean Code", "Robert C. Martin", "Programming", true));
        library.addBook(new Book(4, "Dune", "Frank Herbert", "Sci-Fi", true));
        library.addBook(new Book(5, "Animal Farm", "George Orwell", "Satire", false));

        // 1. List all books
        System.out.println("=== All Books ===");
        for (Book book : library.listAllBooks()) {
            System.out.println(book);
        }

        // 2. Find by ID
        System.out.println("\n=== Find Book by ID ===");
        Book found = library.findBookById(1);
        System.out.println(found);

        // 3. Search by title
        System.out.println("\n=== Search 'Dune' ===");
        List<Book> results = library.searchByTitle("Dune");
        for (Book book : results) {
            System.out.println(book);
        }

        // 4. Remove a book
        System.out.println("\n=== Remove Book ID 5 ===");
        library.removeBookById(5);

        // 5. List again after removal
        System.out.println("\n=== After Removal ===");
        for (Book book : library.listAllBooks()) {
            System.out.println(book);
        }
    }
}