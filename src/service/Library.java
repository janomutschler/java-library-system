package service;

import model.Book;

import java.util.*;

public class Library {

    private List<Book> books;
    private Map<Integer, Book> bookMap;
    private Set<String> genreSet;
    private Deque<String> recentSearches;
	private Deque<String> actionHistory;

    public Library() {
        this.books = new ArrayList<>();
        this.bookMap = new HashMap<>();
        this.genreSet = new HashSet<>();
        this.recentSearches = new ArrayDeque<>();
		this.actionHistory = new ArrayDeque<>();
    }

    public boolean addBook(Book book) {
        if (bookMap.containsKey(book.getId())) {
            return false;
        }

        books.add(book);
        bookMap.put(book.getId(), book);
        genreSet.add(book.getGenre());
        return true;
    }

    public boolean removeBookById(int id) {
        Book book = bookMap.get(id);

        if (book == null) {
            return false;
        }

        books.remove(book);
        bookMap.remove(id);

        return true;
    }

    public Book findBookById(int id) {
        return bookMap.get(id);
    }

    public List<Book> listAllBooks() {
        return books;
    }

    public List<Book> searchByTitle(String keyword) {
        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }

        recentSearches.push(keyword);

        return result;
    }

    public boolean borrowBook(int id) {
        Book book = bookMap.get(id);

        if (book == null || !book.isAvailable()) {
            return false;
        }

        book.setAvailable(false);

        GregorianCalendar today = new GregorianCalendar();
        book.setBorrowDate(today);

        GregorianCalendar dueDate = (GregorianCalendar) today.clone();
        dueDate.add(Calendar.DAY_OF_MONTH, 14);
        book.setDueDate(dueDate);

        return true;
    }

    public boolean returnBook(int id) {
        Book book = bookMap.get(id);

        if (book == null || book.isAvailable()) {
            return false;
        }

        book.setAvailable(true);
        book.setBorrowDate(null);
        book.setDueDate(null);
        return true;
    }

    public Set<String> getGenres() {
        return genreSet;
    }

    public Deque<String> getRecentSearches() {
        return recentSearches;
    }
}