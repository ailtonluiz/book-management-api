package com.ailtonluiz.bookmanager.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Modelo de entrada para autores.
 * Este modelo se utiliza para recibir datos de autores del cliente.
 */
@Getter
@Setter
public class AuthorInputModel {

    /**
     * Identificador único del autor
     */
    private Long id;

    /**
     * Nombre del autor
     */
    private String name;

    /**
     * Identificador de la nacionalidad del autor
     */
    private Long nationalityId;
} 