package com.ailtonluiz.bookmanager.model.exception;

import java.io.Serial;

public class BookNotFoundException extends EntityNotFoundException {
    @Serial
    private static final long serialVersionUID = 1L;

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(Long id) {
        this(String.format("Libro no encontrado con el código %d.",id));
    }

}
