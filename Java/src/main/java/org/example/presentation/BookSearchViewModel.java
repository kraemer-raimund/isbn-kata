package org.example.presentation;

import org.example.domain.Book;
import org.example.domain.BookRepository;

import java.util.Optional;
import java.util.function.Consumer;

public class BookSearchViewModel {

    private final BookRepository bookRepository;
    private final Consumer<String> searchResultHandler;
    private final Consumer<String> bookNotFoundErrorHandler;

    public BookSearchViewModel(
            BookRepository bookRepository,
            Consumer<String> searchResultHandler,
            Consumer<String> bookNotFoundErrorHandler) {
        this.bookRepository = bookRepository;
        this.searchResultHandler = searchResultHandler;
        this.bookNotFoundErrorHandler = bookNotFoundErrorHandler;
    }

    public void searchBook(String isbn) {
        final Optional<Book> exampleBook = bookRepository.byIsbn(isbn);

        exampleBook.ifPresentOrElse(
                this::onBookFound,
                () -> onBookNotFound(isbn)
        );
    }

    private void onBookFound(Book book) {
        var isbn = book.getIsbn();
        var title = book.getTitle();
        var author = book.getAuthor();
        var searchResult = String.format("ISBN: %s\nBook title: %s\nAuthor: %s", isbn, title, author);
        searchResultHandler.accept(searchResult);
    }

    private void onBookNotFound(String isbn) {
        bookNotFoundErrorHandler.accept(isbn);
    }
}
