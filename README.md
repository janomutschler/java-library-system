# Java Library System

A console-based library management system built in Java to practice core language features, collections, file I/O, and object design.

## Features

* Add, remove, and list books
* Search books by title
* Borrow and return books
* Undo the last library action
* Load books from a CSV file
* Save a text report of the current catalog

## Concepts Covered

* `List`, `Map`, `Set`, and `Deque`
* `toString()`, `equals()`, `hashCode()`, and `compareTo()`
* File reading and writing
* String parsing and validation
* Borrow/return state changes with undo support

## Subject Coverage Check

Based on `SUBJECT.md`, the project now covers all required areas:

* [x] Add/remove/display/search books
* [x] Search by title
* [x] Borrow/return with availability tracking
* [x] `List`, `Set`, `Map`, and `Deque/Stack` usage
* [x] CSV/text parsing and string processing
* [x] File load/save
* [x] `toString()`, `equals()`, `hashCode()`, `compareTo()`
* [x] Date handling for borrow and due dates
* [x] Console UI, modular structure, comments/Javadoc

## Project Structure

```text
java-library-system/
├── README.md
├── SUBJECT.md
├── CONCEPTS.md
├── input/
│   └── book_sample.csv
├── output/
└── src/
    ├── Main.java
    ├── io/
    │   └── FileManager.java
    ├── model/
    │   ├── Book.java
    │   └── User.java
    ├── service/
    │   ├── HistoryManager.java
    │   └── Library.java
    ├── ui/
    │   └── ConsoleApp.java
    └── util/
        └── InputHelper.java
```

## Run It

From the project root:

```bash
cd src/
javac Main.java
java Main
```

## Input Format

The file loader expects rows in this format:

```text
id;title;author;genre;available
```

Example:

```text
1;Clean Code;Robert C. Martin;Programming;true
```

## Notes

* `output/` is kept in the repository so reports have a place to go, but generated files should not be committed.
* The project is intentionally small and focused on learning the standard Java class library.

---
