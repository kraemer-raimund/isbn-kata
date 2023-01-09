package org.example;

import org.example.domain.Book;
import org.example.domain.BookRepository;
import org.example.persistence.FakeInMemoryBookRepository;

import java.util.Optional;

public class Main {

    private static final BookRepository bookRepository = new FakeInMemoryBookRepository();

    public static void main(String[] args) {
        final Optional<Book> exampleBook = bookRepository.byIsbn("978-3-16-148410-0");

        exampleBook.ifPresentOrElse(
                book -> System.out.printf("%s, %s, %s\n", book.getIsbn(), book.getName(), book.getAuthor()),
                () -> System.out.println("No book found for the provided ISBN.")
        );
    }
}
