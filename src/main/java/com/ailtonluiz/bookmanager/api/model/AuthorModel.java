package com.ailtonluiz.bookmanager.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class AuthorModel {

    private Long id;

    private String name;

    private Boolean enabled;

    private NationalitySummaryModel nationality;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}
