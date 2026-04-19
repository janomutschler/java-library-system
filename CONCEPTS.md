# CONCEPTS.md

## Purpose of this Document

This document summarizes the most important concepts from the course
**“Data Structures and Java Class Library”**.

The goal is not just to memorize definitions, but to:

* understand when to use each concept
* understand how they interact
* apply them in a real project (Java Library System)

---

# 1. Programming Style & Conventions

## Why it matters

Code is read more often than it is written. Good style makes code understandable.

## Key Rules

* Packages → lowercase (`com.project.library`)
* Classes → UpperCamelCase (`Book`)
* Methods → lowerCamelCase (`getTitle`)
* Constants → UPPER_CASE (`MAX_SIZE`)
* Methods should be verbs (`addBook`, `removeBook`)

## Comments

* `//` → short explanation
* `/* */` → longer explanation
* `/** */` → Javadoc (used to generate documentation)

## Annotations

* `@Override` → ensures method correctly overrides parent
* `@Deprecated` → marks old code

👉 **In my project**

* Use clean naming everywhere
* Add Javadoc to important classes

---

# 2. Working with Objects

## toString()

Converts an object to a readable string.

Default:

```java
Book@1a2b3c
```

Override:

```java
@Override
public String toString() {
    return title + " by " + author;
}
```

👉 Used for debugging and printing objects.

---

## == vs equals()

### `==`

* compares memory reference
* “are these the same object?”

### `equals()`

* compares content
* “are these logically equal?”

👉 Always override `equals()` for real objects.

---

## hashCode()

Used for hashing (important for `HashMap`, `HashSet`).

Rule:

* if `equals()` is true → hashCode must be equal

👉 Used for fast lookup.

---

## compareTo()

Used for sorting.

Returns:

* negative → smaller
* 0 → equal
* positive → greater

Example:

```java
@Override
public int compareTo(Book other) {
    return this.title.compareTo(other.title);
}
```

---

# 3. External Packages & Libraries

## Importing

```java
import java.util.List;
```

Purpose:

* reuse existing code
* avoid reinventing the wheel

## Java Standard Library

Most important packages:

* `java.util` → collections
* `java.io` → files
* `java.lang` → core (String, Object)

👉 You will use this constantly.

---

# 4. Data Structures (CORE PART)

## Arrays

Fixed size, fast access.

```java
int[] arr = new int[5];
```

Pros:

* very fast access

Cons:

* fixed size
* manual management

---

## Collections Framework

More flexible than arrays.

### List

Ordered, allows duplicates.

Implementations:

* `ArrayList` → fast access
* `LinkedList` → fast insert/delete

👉 Use for storing books.

---

### Set

No duplicates.

Implementations:

* `HashSet` → fast
* `TreeSet` → sorted

👉 Use for unique genres or IDs.

---

### Map

Key → Value structure.

Example:

```java
Map<Integer, Book> books;
```

Implementations:

* `HashMap` → fast lookup
* `TreeMap` → sorted keys

👉 Use for finding books by ID.

---

### Stack (LIFO)

Last In → First Out

Main operations:

* push
* pop
* peek

👉 Use for undo functionality.

---

### Queue (FIFO)

First In → First Out

👉 Use for processing tasks in order.

---

# 5. Strings

## Important Methods

* `contains()`
* `startsWith()`
* `endsWith()`
* `indexOf()`
* `lastIndexOf()`
* `substring()`
* `replace()`
* `split()`

Example:

```java
String[] parts = line.split(";");
```

---

## Important Concept: Immutability

Strings cannot be changed.

```java
String s = "Hello";
s.replace("H", "J"); // creates NEW string
```

👉 Always remember: operations return new strings.

---

## StringBuffer

Used when building large strings efficiently.

```java
StringBuffer sb = new StringBuffer();
sb.append("Hello");
```

---

# 6. Date & Time

## Date (basic, outdated in parts)

Represents a timestamp.

## Calendar / GregorianCalendar

More flexible.

Example:

```java
calendar.add(Calendar.DAY_OF_MONTH, 7);
```

👉 Use for:

* due dates
* deadlines

---

# 7. File System

## File class

Represents files and directories.

```java
File file = new File("books.txt");
```

Important methods:

* `exists()`
* `isFile()`
* `mkdir()`
* `renameTo()` → move file

---

# 8. File I/O (Streams)

## Concept

Data flows from:

* source → program → destination

---

## Reading Files

```java
BufferedReader reader = new BufferedReader(new FileReader(file));
```

Read line:

```java
String line = reader.readLine();
```

---

## Writing Files

```java
FileWriter writer = new FileWriter(file);
writer.write("text");
```

---

## Exceptions

Always handle:

* `IOException`
* `FileNotFoundException`

---

# 9. Iteration

## Iterator

```java
Iterator<Book> it = books.iterator();
```

Methods:

* `hasNext()`
* `next()`
* `remove()`

---

## Enhanced for-loop

```java
for (Book b : books) {
    System.out.println(b);
}
```

---

# 10. Key Takeaways

## What this course is REALLY about

Not syntax.

It’s about:

* choosing the right data structure
* understanding object behavior
* using the Java standard library effectively

---

## Mental Model

When solving a problem, always ask:

1. How should data be stored?
   → List, Set, Map?

2. How should objects be compared?
   → equals / hashCode / compareTo?

3. How does data enter/leave the system?
   → files, streams

4. How is data processed?
   → strings, loops, collections

---

## Final Thought

If you understand:

* Collections
* Object comparison
* Strings
* File handling

you understand **80–90% of this course**.

Everything else is detail.

---
