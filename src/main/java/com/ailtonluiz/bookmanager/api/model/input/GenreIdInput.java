package com.ailtonluiz.bookmanager.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreIdInput {

    @NotNull
    private Long id;

}
