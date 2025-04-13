package com.ailtonluiz.bookmanager.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Modelo de género para la API.
 * Este modelo representa un género literario en el sistema.
 */
@Getter
@Setter
public class GenreModel {

    /**
     * Identificador único del género
     */
    private Long id;

    /**
     * Nombre del género
     */
    private String name;

    /**
     * Estado indicando si el género está activo
     */
    private Boolean enabled;

    /**
     * Fecha y hora de creación del género
     */
    private String createdAt;

    /**
     * Fecha y hora de última actualización del género
     */
    private String updatedAt;
}
