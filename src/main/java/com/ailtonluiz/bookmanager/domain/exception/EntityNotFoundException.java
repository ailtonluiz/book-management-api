package com.ailtonluiz.bookmanager.domain.exception;

import java.io.Serial;

public class EntityNotFoundException extends TransactionException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }


}
