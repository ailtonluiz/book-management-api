package com.ailtonluiz.bookmanager.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorInput {

    @NotBlank(message = "El nombre del autor no puede estar vacío")
    private String name;

    @Valid
    @NotNull(message = "La nacionalidad no puede ser nula")
    private NationalityIdInput nationality;

}
