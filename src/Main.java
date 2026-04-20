import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Book;

public class Main {
    public static void main(String[] args) {
        Book b1 = new Book(1, "Dune", "Frank Herbert", "Sci-Fi", true);
        Book b2 = new Book(2, "1984", "George Orwell", "Dystopia", true);
        Book b3 = new Book(3, "Clean Code", "Robert C. Martin", "Programming", true);
        Book b4 = new Book(4, "Dune", "Frank Herbert", "Sci-Fi", true);
        Book b5 = new Book(5, "Animal Farm", "George Orwell", "Satire", false);
        Book dub1 = new Book(1, "Dune", "Frank Herbert", "Sci-Fi", true);

        List<Book> books = new ArrayList<>();
        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);
        books.add(b5);

        System.out.println("=== Original books ===");
        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("\n=== Comparison ===");
        System.out.println("b1 == dub1: " + (b1 == dub1));
        System.out.println("b1.equals(dub1): " + b1.equals(dub1));
        System.out.println("b1.hashCode(): " + b1.hashCode());
        System.out.println("dub1.hashCode(): " + dub1.hashCode());

        System.out.println("\n=== Sorted books ===");
        Collections.sort(books);
        for (Book book : books) {
            System.out.println(book);
        }
    }
}