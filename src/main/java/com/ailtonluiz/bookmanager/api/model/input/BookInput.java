package com.ailtonluiz.bookmanager.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookInput {

    private String title;

    private String description;

    private GenreIdInput genre;

    private AuthorIdInput author;

    private Integer year;

    private String photoUrl;
}
