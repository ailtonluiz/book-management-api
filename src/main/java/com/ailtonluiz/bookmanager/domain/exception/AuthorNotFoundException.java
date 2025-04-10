package com.ailtonluiz.bookmanager.domain.exception;

import java.io.Serial;

public class AuthorNotFoundException extends EntityNotFoundException {
    @Serial
    private static final long serialVersionUID = 1L;

    public AuthorNotFoundException(String message) {
        super(message);
    }

    public AuthorNotFoundException(Long id) {
        this(String.format("Autor no encontrado con el código %d.",id));
    }

}
