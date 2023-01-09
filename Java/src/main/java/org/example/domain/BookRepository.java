package org.example.domain;

import java.util.Optional;

public interface BookRepository {

    Optional<Book> byIsbn(String isbn);
}
