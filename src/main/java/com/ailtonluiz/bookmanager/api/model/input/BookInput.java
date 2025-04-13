package com.ailtonluiz.bookmanager.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookInput {

    @NotBlank(message = "El título del libro no puede estar vacío")
    private String title;

    @Valid
    @NotNull(message = "El autor no puede ser nulo")
    private AuthorIdInput author;

    @Valid
    @NotNull(message = "El género no puede ser nulo")
    private GenreIdInput genre;

    @NotNull(message = "El año de publicación no puede ser nulo")
    @Positive(message = "El año de publicación debe ser positivo")
    private Integer year;

    private String description;

    private String photoUrl;
}
