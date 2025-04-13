package com.ailtonluiz.bookmanager.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreIdInput {

    @NotNull(message = "El ID del género no puede ser nulo")
    private Long id;

}
