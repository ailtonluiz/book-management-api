package com.ailtonluiz.bookmanager.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Modelo de entrada para géneros.
 * Este modelo se utiliza para recibir datos de géneros del cliente.
 */
@Getter
@Setter
public class GenreInputModel {

    /**
     * Identificador único del género
     */
    private Long id;

    /**
     * Nombre del género
     */
    private String name;
} 