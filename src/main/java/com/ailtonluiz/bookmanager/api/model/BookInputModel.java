package com.ailtonluiz.bookmanager.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Modelo de entrada para libros.
 * Este modelo se utiliza para recibir datos de libros del cliente.
 */
@Getter
@Setter
public class BookInputModel {

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
     * Identificador del autor del libro
     */
    private Long authorId;

    /**
     * Identificador del género del libro
     */
    private Long genreId;
} 