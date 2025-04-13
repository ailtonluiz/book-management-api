package com.ailtonluiz.bookmanager.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NationalityIdInput {

    @NotNull(message = "El ID de la nacionalidad no puede ser nulo")
    private Long id;

}
