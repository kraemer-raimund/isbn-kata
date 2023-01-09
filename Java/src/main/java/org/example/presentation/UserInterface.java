package org.example.presentation;

import org.example.domain.Book;
import org.example.domain.BookRepository;

import java.util.Optional;

public class UserInterface {

    private final BookRepository bookRepository;

    public UserInterface(BookRepository repository) {
        this.bookRepository = repository;
    }

    public void searchBook(String isbn) {
        final Optional<Book> exampleBook = bookRepository.byIsbn(isbn);

        exampleBook.ifPresentOrElse(
                this::showResult,
                () -> showBookNotFoundError(isbn)
        );
    }

    private void showResult(Book book) {
        System.out.printf("%s, %s, %s\n", book.getIsbn(), book.getName(), book.getAuthor());
    }

    private void showBookNotFoundError(String isbn) {
        System.out.printf("No book found for the provided ISBN %s.", isbn);
    }
}
