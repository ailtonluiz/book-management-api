package com.ailtonluiz.bookmanager.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

/**
 * Modelo de representación de un libro para la API.
 * Este modelo se utiliza para transferir datos de libros entre el cliente y el servidor.
 */
@Getter
@Setter
public class BookModel {

    /**
     * Identificador único del libro
     */
    private Long id;

    /**
     * Título del libro
     */
    private String title;

    /**
     * Descripción o resumen del libro
     */
    private String description;

    /**
     * Año de publicación del libro
     */
    private Integer year;

    /**
     * URL de la portada del libro
     */
    private String photoUrl;

    /**
     * Información resumida del autor del libro
     */
    private AuthorSummaryModel author;

    /**
     * Información resumida del género del libro
     */
    private GenreSummaryModel genre;

    /**
     * Indica si el libro está activo en el sistema
     */
    private Boolean enabled;

    /**
     * Fecha y hora de creación del registro
     */
    private OffsetDateTime createdAt;

    /**
     * Fecha y hora de la última actualización del registro
     */
    private OffsetDateTime updatedAt;
}
