package io;

import model.Book;
import service.Library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles reading and writing library data files.
 */
public class FileManager {

    /**
     * Loads books from a semicolon-separated file.
     */
    public List<Book> loadBooksFromFile(String path) {
        List<Book> loadedBooks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                // Skip optional header row.
                if (lineNumber == 1 && line.toLowerCase().startsWith("id;")) {
                    continue;
                }

                try {
                    Book book = parseBook(line);
                    loadedBooks.add(book);
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping invalid line " + lineNumber + ": " + line);
                    System.out.println("Reason: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + path);
            System.out.println(e.getMessage());
        }

        return loadedBooks;
    }

    private Book parseBook(String line) {
        String[] parts = line.split(";");

        if (parts.length != 5) {
            throw new IllegalArgumentException("Line must contain exactly 5 values: id;title;author;genre;available");
        }

        String idPart = parts[0].trim();
        String title = parts[1].trim();
        String author = parts[2].trim();
        String genre = parts[3].trim();
        String availablePart = parts[4].trim().toLowerCase();

        int id;
        try {
            id = Integer.parseInt(idPart);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID must be a valid integer");
        }

        if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
            throw new IllegalArgumentException("Title, author, and genre must not be empty");
        }

        boolean available;
        if (availablePart.equals("true")) {
            available = true;
        } else if (availablePart.equals("false")) {
            available = false;
        } else {
            throw new IllegalArgumentException("Available must be 'true' or 'false'");
        }

        return new Book(id, title, author, genre, available);
    }

    /**
     * Writes a catalog summary report to disk.
     */
    public void saveReport(String path, Library library) {
        try (FileWriter writer = new FileWriter(path)) {
            List<Book> books = library.listAllBooks();
            Set<String> genres = library.getGenres();

            int availableCount = 0;
            int borrowedCount = 0;

            for (Book book : books) {
                if (book.isAvailable()) {
                    availableCount++;
                } else {
                    borrowedCount++;
                }
            }

            writer.write("=== Library Report ===\n");
            writer.write("Total books: " + books.size() + "\n");
            writer.write("Available books: " + availableCount + "\n");
            writer.write("Borrowed books: " + borrowedCount + "\n");
            writer.write("Genres: " + genres + "\n");
            writer.write("\n=== Book List ===\n");

            for (Book book : books) {
                writer.write(book.toString() + "\n");
            }

            System.out.println("Report saved successfully to: " + path);
        } catch (IOException e) {
            System.out.println("Error writing report to file: " + path);
            System.out.println(e.getMessage());
        }
    }
}