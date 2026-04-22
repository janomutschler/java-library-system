package service;

import model.Book;

import java.util.*;

public class Library {

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

        return result;
    }

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
        dueDate.add(Calendar.DAY_OF_MONTH, 14);
        book.setDueDate(dueDate);

        return true;
    }

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

    public boolean undoLastAction() {
        HistoryManager.Action action = historyManager.undoLastAction();

        if (action == null) {
            return false;
        }

        Book snapshot = action.getBookSnapshot();

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

    public Set<String> getGenres() {
        return genreSet;
    }
}