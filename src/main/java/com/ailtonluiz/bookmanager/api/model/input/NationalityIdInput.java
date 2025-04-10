package com.ailtonluiz.bookmanager.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NationalityIdInput {

    @NotNull
    private Long id;

}
