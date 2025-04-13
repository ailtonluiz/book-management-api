package com.ailtonluiz.bookmanager.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreInput {

    @NotBlank(message = "El nombre del género no puede estar vacío")
    private String name;

}
