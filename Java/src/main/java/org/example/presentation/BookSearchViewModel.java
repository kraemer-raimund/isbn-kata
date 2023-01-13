package org.example.presentation;

import org.example.domain.Book;
import org.example.domain.BookRepository;

import java.util.Optional;
import java.util.function.Consumer;

public class BookSearchViewModel {

    private final BookRepository bookRepository;
    private final Consumer<String> searchResultHandler;
    private final Consumer<String> illFormedIsbnErrorHandler;
    private final Consumer<String> bookNotFoundErrorHandler;

    public BookSearchViewModel(
            BookRepository bookRepository,
            Consumer<String> searchResultHandler,
            Consumer<String> illFormedIsbnErrorHandler,
            Consumer<String> bookNotFoundErrorHandler) {
        this.bookRepository = bookRepository;
        this.searchResultHandler = searchResultHandler;
        this.illFormedIsbnErrorHandler = illFormedIsbnErrorHandler;
        this.bookNotFoundErrorHandler = bookNotFoundErrorHandler;
    }

    public void searchBook(String isbn) {
        if (!isWellFormedIsbn(isbn)) {
            onIllFormedIsbn(isbn);
            return;
        }

        final Optional<Book> exampleBook = bookRepository.byIsbn(isbn);
        exampleBook.ifPresentOrElse(
                this::onBookFound,
                () -> onBookNotFound(isbn)
        );
    }

    private boolean isWellFormedIsbn(String potentialIsbn) {
        // For now, we only accept this hardcoded string. In the following steps, we will implement
        // the actual validation rules.
        return potentialIsbn.equals("978-3-16-148410-0");
    }

    private void onBookFound(Book book) {
        var searchResult = String.format("%s, %s, %s", book.getIsbn(), book.getTitle(), book.getAuthor());
        searchResultHandler.accept(searchResult);
    }

    private void onIllFormedIsbn(String illFormedIsbn) {
        illFormedIsbnErrorHandler.accept(illFormedIsbn);
    }

    private void onBookNotFound(String isbn) {
        bookNotFoundErrorHandler.accept(isbn);
    }
}
