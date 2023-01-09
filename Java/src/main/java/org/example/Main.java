package org.example;

import org.example.domain.BookRepository;
import org.example.persistence.FakeInMemoryBookRepository;
import org.example.presentation.UserInterface;

public class Main {

    private static final BookRepository bookRepository = new FakeInMemoryBookRepository();
    private static final UserInterface userInterface = new UserInterface(bookRepository);

    public static void main(String[] args) {
        userInterface.searchBook("978-3-16-148410-0");
    }
}
