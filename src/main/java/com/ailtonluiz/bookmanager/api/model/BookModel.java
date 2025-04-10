package com.ailtonluiz.bookmanager.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class BookModel {

    private Long id;

    private String title;

    private String description;

    private Integer year;

    private GenreSummaryModel genre;

    private String photoUrl;

    private AuthorSummaryModel author;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;



}
