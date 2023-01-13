package org.example.presentation;

import org.example.domain.Book;
import org.example.domain.BookRepository;
import org.example.utils.StringUtils;

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
        if (!StringUtils.isWellFormedIsbn(isbn)) {
            onIllFormedIsbn(isbn);
            return;
        }

        final Optional<Book> exampleBook = bookRepository.byIsbn(isbn);
        exampleBook.ifPresentOrElse(
                this::onBookFound,
                () -> onBookNotFound(isbn)
        );
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
