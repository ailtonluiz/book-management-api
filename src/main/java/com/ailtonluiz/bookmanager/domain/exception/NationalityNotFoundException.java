package com.ailtonluiz.bookmanager.domain.exception;

import java.io.Serial;

public class NationalityNotFoundException extends EntityNotFoundException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NationalityNotFoundException(String message) {
        super(message);
    }

    public NationalityNotFoundException(Long id) {
        this(String.format("Nacionalidad no encontrada con el código %d.",id));
    }

}
