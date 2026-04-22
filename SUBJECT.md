# Java Library System

## Objective

The goal of this project is to develop a console-based library management system in Java to deepen understanding of:

* Object-oriented programming principles
* Java standard class library
* Data structures and collections
* String processing
* File system interaction and data streams

The system should simulate a small library where books can be managed, searched, borrowed, and returned.

---

## Functional Requirements

The application must provide the following features:

### 1. Book Management

* Add new books
* Remove books
* Display all books
* Search books by title

### 2. Borrowing System

* Borrow a book
* Return a book
* Track availability status

### 3. Data Structures Usage

The system must use:

* `List` for ordered storage of books
* `Set` to prevent duplicates (e.g. ISBN or titles)
* `Map` for fast lookup by ID
* `Deque` or `Stack` for undo functionality

### 4. String Processing

* Parse book data from text/CSV files
* Use methods such as `split`, `substring`, `indexOf`, `lastIndexOf`, etc.

### 5. File Handling

* Load books from a file
* Save reports or current state to a file

### 6. Object-Oriented Features

* Implement `toString()`, `equals()`, and `hashCode()`
* Use `compareTo()` for sorting

### 7. Date Handling (Optional but recommended)

* Track borrow dates
* Calculate due dates

---

## Non-Functional Requirements

* Console-based interface
* Clean code structure following Java conventions
* Use of comments and/or Javadoc
* Modular and maintainable design

---

## Learning Goals

By completing this project, you should be able to:

* Choose appropriate data structures for different use cases
* Work with Java collections effectively
* Understand object comparison in Java
* Handle file input/output
* Process and manipulate strings
* Structure a medium-sized Java application

---
