package com.ailtonluiz.bookmanager.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NationalityInput {

    @NotBlank(message = "El nombre de la nacionalidad no puede estar vacío")
    private String name;

}
