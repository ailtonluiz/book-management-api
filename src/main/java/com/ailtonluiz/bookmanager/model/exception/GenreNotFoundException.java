package com.ailtonluiz.bookmanager.model.exception;

import java.io.Serial;

public class GenreNotFoundException extends EntityNotFoundException {
    @Serial
    private static final long serialVersionUID = 1L;

    public GenreNotFoundException(String message) {
        super(message);
    }

    public GenreNotFoundException(Long id) {
        this(String.format("Genero no encontrado con el código %d.",id));
    }

}
