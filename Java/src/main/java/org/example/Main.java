package org.example;

import org.assertj.core.util.Strings;
import org.example.domain.BookRepository;
import org.example.persistence.FakeInMemoryBookRepository;
import org.example.presentation.UserInterface;

public class Main {

    private static final BookRepository bookRepository = new FakeInMemoryBookRepository();
    private static final UserInterface userInterface = new UserInterface(bookRepository);

    public static void main(String[] args) {
        if (isCommandLineArgumentProvided(args)) {
            userInterface.searchBook(args[0]);
        } else {
            // Provide the ISBN here if you do not want to use the command line arguments.
            userInterface.searchBook("978-3-16-148410-0");
        }
    }

    private static boolean isCommandLineArgumentProvided(String[] args) {
        return args.length > 0 && !Strings.isNullOrEmpty(args[0]);
    }
}
