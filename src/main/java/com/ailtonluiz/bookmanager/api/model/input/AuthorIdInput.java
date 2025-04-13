package com.ailtonluiz.bookmanager.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorIdInput {

    @NotNull(message = "El ID del autor no puede ser nulo")
    private Long id;

}
