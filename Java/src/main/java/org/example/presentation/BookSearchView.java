package org.example.presentation;

import org.example.DIContainer;

public class BookSearchView {

    private static final BookSearchView bookSearchView = new BookSearchView();
    private final BookSearchViewModel bookSearchViewModel = DIContainer.instantiateBookSearchViewModel(
            this::showSearchResult, this::showInvalidIsbnErrorMessage, this::showBookNotFoundErrorMessage);

    public static void main(String[] args) {
        bookSearchView.onSearchSubmitted(args);
    }

    private void onSearchSubmitted(String[] args) {
        if (isCommandLineArgumentProvided(args)) {
            bookSearchViewModel.searchBook(args[0]);
        } else {
            // Provide the ISBN here if you do not want to use the command line arguments.
            bookSearchViewModel.searchBook("978-3-16-148410-0");
        }
    }

    private void showSearchResult(String result) {
        System.out.println(result);
    }

    private void showInvalidIsbnErrorMessage(String illFormedIsbn) {
        var errorMessage = String.format("The provided search phrase `%s` is not a well-formed ISBN.", illFormedIsbn);
        System.out.println(errorMessage);
    }

    private void showBookNotFoundErrorMessage(String isbn) {
        var errorMessage = String.format("No book found for the provided ISBN %s.", isbn);
        System.out.println(errorMessage);
    }

    private static boolean isCommandLineArgumentProvided(String[] args) {
        return args.length > 0
                && !(args[0] == null)
                && !(args[0].isBlank());
    }
}
