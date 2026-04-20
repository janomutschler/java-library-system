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

    public void addBook(Book book) {
        books.add(book);
        bookMap.put(book.getId(), book);
        genreSet.add(book.getGenre());
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

    public Set<String> getGenres() {
        return genreSet;
    }

    public Deque<String> getRecentSearches() {
        return recentSearches;
    }
}