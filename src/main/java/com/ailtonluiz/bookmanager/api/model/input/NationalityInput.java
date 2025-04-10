package com.ailtonluiz.bookmanager.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NationalityInput {

    @NotBlank
    private String name;

}
