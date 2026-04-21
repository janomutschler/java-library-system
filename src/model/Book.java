package model;

import java.util.Objects;
import java.util.GregorianCalendar;

public class Book implements Comparable<Book> {

    private final int id;
    private final String title;
    private final String author;
    private final String genre;
    private boolean available;
    private GregorianCalendar borrowDate;
    private GregorianCalendar dueDate;

    public Book(int id, String title, String author, String genre, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = available;
        this.borrowDate = null;
        this.dueDate = null;
    }

    //getter
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public GregorianCalendar getBorrowDate() {
        return borrowDate;
    }

    public GregorianCalendar getDueDate() {
        return dueDate;
    }

    //setter
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setBorrowDate(GregorianCalendar borrowDate) {
        this.borrowDate = borrowDate;
    }
    public void setDueDate(GregorianCalendar dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String status = available ? "Available" : "Borrowed";

        String dateInfo = "";
        if (borrowDate != null && dueDate != null) {
            dateInfo = String.format(
                " | Borrowed: %tF | Due: %tF",
                borrowDate,
                dueDate
            );
        }

        return String.format(
            "%-5d %-20s | %-20s | %-15s | %s%s",
            id,
            title,
            author,
            genre,
            status,
            dateInfo
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Book other = (Book) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Book other) {
        // Sort by title (case-insensitive)
        return this.title.compareToIgnoreCase(other.title);
    }
}