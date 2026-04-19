# Java Library System (in early Development no stable version yet)

A console-based library management system built in Java to practice core concepts of data structures and the Java class library.

## 🚀 Overview

This project simulates a simple library where books can be managed, searched, borrowed, and returned.
It is designed as a hands-on way to learn and apply key Java concepts such as collections, object comparison, string processing, and file handling.

---

## 🧩 Features

* Add, remove, and list books
* Search books by title or author
* Borrow and return books
* Undo last actions (history system)
* Load data from files
* Save reports to files
* Handle structured text input (CSV-like format)

---

## 🛠️ Tech & Concepts

This project focuses on:

* Java Collections (`List`, `Set`, `Map`, `Deque`)
* Object methods (`toString`, `equals`, `hashCode`, `compareTo`)
* File I/O (`File`, `BufferedReader`, `FileWriter`)
* String processing (`split`, `substring`, `indexOf`, etc.)
* Basic date handling (`Calendar` / `Date`)
* Clean code & Java conventions

---

## 📁 Project Structure

```text
java-library-system/
├── README.md
├── SUBJECT.md
├── CONCEPTS.md
├── .gitignore
├── input/
│   └── books_sample.csv
├── output/
│   └── .gitkeep
└── src/
    ├── Main.java
    ├── model/
    │   ├── Book.java
    │   ├── User.java
    │   └── Loan.java
    ├── service/
    │   ├── Library.java
    │   ├── LoanService.java
    │   └── HistoryManager.java
    ├── io/
    │   └── FileManager.java
    ├── util/
    │   ├── InputHelper.java
    │   ├── DateHelper.java
    │   └── StringHelper.java
    └── data/
        └── SampleData.java
```

---

## ▶️ How to Run

1. Compile:

```bash
javac Main.java
```

2. Run:

```bash
java Main
```

---

## 📚 Purpose

This project was built as part of learning:
**Data Structures and Java Class Library**

The focus is not on building a production-ready system, but on understanding how Java’s standard tools work together in practice.

---

## 💡 Future Improvements

* Add GUI (JavaFX / Swing)
* Add database persistence
* Improve search and filtering
* Add user authentication

---
