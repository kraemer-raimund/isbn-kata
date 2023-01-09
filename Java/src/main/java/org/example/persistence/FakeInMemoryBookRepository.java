package org.example.persistence;

import org.example.domain.Book;
import org.example.domain.BookRepository;

import java.util.Map;
import java.util.Optional;

public class FakeInMemoryBookRepository implements BookRepository {

    private final Map<String, Book> bookByIsbn = Map.of(
            "978-3-16-148410-0", new Book("978-3-16-148410-0", "Example Book", "Jane Doe")
    );

    @Override
    public Optional<Book> byIsbn(String isbn) {
        if (!bookByIsbn.containsKey(isbn)) {
            return Optional.empty();
        }

        return Optional.of(bookByIsbn.get(isbn));
    }
}
