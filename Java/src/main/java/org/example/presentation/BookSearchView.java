package org.example.presentation;

import org.example.DIContainer;

import javax.swing.*;
import java.awt.*;

public class BookSearchView extends JFrame {

    private static final String exampleIsbn = "978-3-16-148410-0";

    private final BookSearchViewModel bookSearchViewModel = DIContainer.instantiateBookSearchViewModel(
            this::showSearchResult,
            this::showBookNotFoundErrorMessage
    );

    private final JTextPane resultTextPane;

    public BookSearchView(JTextPane resultTextPane) {
        this.resultTextPane = resultTextPane;
    }

    public static void main(String[] args) {
        final var panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 400));

        final var isbnLabel = new JLabel("ISBN");
        panel.add(isbnLabel);

        final var isbnTextField = new JTextField();
        isbnTextField.setPreferredSize(new Dimension(200, 24));
        isbnTextField.setText(exampleIsbn);
        panel.add(isbnTextField);

        final var searchButton = new JButton("Search");
        panel.add(searchButton);

        final var resultPane = new JTextPane();
        resultPane.setPreferredSize(panel.getPreferredSize());
        resultPane.setEditable(false);
        panel.add(resultPane);

        final var view = new BookSearchView(resultPane);
        view.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8));
        view.add(panel);
        view.pack();
        view.setTitle("Book Library Plus – Premium Gold Edition Season Pass");
        view.setVisible(true);
        view.setResizable(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        searchButton.addActionListener(event -> view.onSearchSubmitted(isbnTextField.getText()));
    }

    public void onSearchSubmitted(String isbn) {
        bookSearchViewModel.searchBook(isbn);
    }

    private void showSearchResult(String result) {
        resultTextPane.setText(result);
    }

    private void showBookNotFoundErrorMessage(String isbn) {
        var errorMessage = String.format("No book found for the provided ISBN %s.", isbn);
        resultTextPane.setText(errorMessage);
    }
}
