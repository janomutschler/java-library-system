package service;

import model.Book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Core library service that manages catalog state and borrowing rules.
 */
public class Library {

    private static final int LOAN_DAYS = 14;

    private final List<Book> books;
    private final Map<Integer, Book> bookMap;
    private final Set<String> genreSet;
    private final HistoryManager historyManager;

    public Library() {
        this.books = new ArrayList<>();
        this.bookMap = new HashMap<>();
        this.genreSet = new HashSet<>();
        this.historyManager = new HistoryManager();
    }

    /**
     * Adds a book if its ID is unique.
     */
    public boolean addBook(Book book) {
        return addBookInternal(book, true);
    }

    private boolean addBookInternal(Book book, boolean recordHistory) {
        if (bookMap.containsKey(book.getId())) {
            return false;
        }

        books.add(book);
        bookMap.put(book.getId(), book);
        genreSet.add(book.getGenre());

        if (recordHistory) {
            historyManager.record(HistoryManager.ActionType.ADD, book);
        }

        return true;
    }

    /**
     * Removes a book by ID.
     */
    public boolean removeBookById(int id) {
        return removeBookByIdInternal(id, true);
    }

    private boolean removeBookByIdInternal(int id, boolean recordHistory) {
        Book book = bookMap.get(id);

        if (book == null) {
            return false;
        }

        if (recordHistory) {
            historyManager.record(HistoryManager.ActionType.REMOVE, book);
        }

        books.remove(book);
        bookMap.remove(id);
        removeGenreIfUnused(book.getGenre());

        return true;
    }

    /**
     * Returns the book with the given ID, or null if missing.
     */
    public Book findBookById(int id) {
        return bookMap.get(id);
    }

    /**
     * Returns all books currently stored in the catalog.
     */
    public List<Book> listAllBooks() {
        return books;
    }

    /**
     * Performs a case-insensitive title search.
     */
    public List<Book> searchByTitle(String keyword) {
        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }

        return result;
    }

    /**
     * Borrows a book if it exists and is currently available.
     */
    public boolean borrowBook(int id) {
        return borrowBookInternal(id, true);
    }

    private boolean borrowBookInternal(int id, boolean recordHistory) {
        Book book = bookMap.get(id);

        if (book == null || !book.isAvailable()) {
            return false;
        }

        if (recordHistory) {
            historyManager.record(HistoryManager.ActionType.BORROW, book);
        }

        book.setAvailable(false);

        GregorianCalendar today = new GregorianCalendar();
        book.setBorrowDate(today);

        GregorianCalendar dueDate = (GregorianCalendar) today.clone();
        dueDate.add(Calendar.DAY_OF_MONTH, LOAN_DAYS);
        book.setDueDate(dueDate);

        return true;
    }

    /**
     * Returns a borrowed book and clears its loan dates.
     */
    public boolean returnBook(int id) {
        return returnBookInternal(id, true);
    }

    private boolean returnBookInternal(int id, boolean recordHistory) {
        Book book = bookMap.get(id);

        if (book == null || book.isAvailable()) {
            return false;
        }

        if (recordHistory) {
            historyManager.record(HistoryManager.ActionType.RETURN, book);
        }

        book.setAvailable(true);
        book.setBorrowDate(null);
        book.setDueDate(null);
        return true;
    }

    /**
     * Reverts the most recent successful mutating action.
     */
    public boolean undoLastAction() {
        HistoryManager.Action action = historyManager.undoLastAction();

        if (action == null) {
            return false;
        }

        Book snapshot = action.getBookSnapshot();

        // Reverse action by applying the opposite operation or restoring state.
        switch (action.getType()) {
            case ADD:
                return removeBookByIdInternal(snapshot.getId(), false);
            case REMOVE:
                return addBookInternal(snapshot, false);
            case BORROW:
            case RETURN:
                return restoreBookState(snapshot);
            default:
                return false;
        }
    }

    private boolean restoreBookState(Book snapshot) {
        Book book = bookMap.get(snapshot.getId());

        if (book == null) {
            return false;
        }

        book.setAvailable(snapshot.isAvailable());
        book.setBorrowDate(snapshot.getBorrowDate());
        book.setDueDate(snapshot.getDueDate());

        return true;
    }

    private void removeGenreIfUnused(String genre) {
        for (Book currentBook : books) {
            if (currentBook.getGenre().equals(genre)) {
                return;
            }
        }

        genreSet.remove(genre);
    }

    /**
     * Returns all genres currently present in the catalog.
     */
    public Set<String> getGenres() {
        return genreSet;
    }
}
