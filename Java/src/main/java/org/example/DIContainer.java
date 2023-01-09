package org.example;

import org.example.domain.BookRepository;
import org.example.persistence.FakeInMemoryBookRepository;
import org.example.presentation.BookSearchViewModel;

import java.util.function.Consumer;

public class DIContainer {

    private static final BookRepository bookRepository = new FakeInMemoryBookRepository();

    public static BookSearchViewModel instantiateBookSearchViewModel(
            Consumer<String> searchResultHandler,
            Consumer<String> bookNotFoundErrorHandler) {
        return new BookSearchViewModel(bookRepository, searchResultHandler, bookNotFoundErrorHandler);
    }
}
