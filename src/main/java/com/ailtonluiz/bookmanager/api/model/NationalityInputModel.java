package com.ailtonluiz.bookmanager.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Modelo de entrada para nacionalidades.
 * Este modelo se utiliza para recibir datos de nacionalidades del cliente.
 */
@Getter
@Setter
public class NationalityInputModel {

    /**
     * Identificador único de la nacionalidad
     */
    private Long id;

    /**
     * Nombre de la nacionalidad
     */
    private String name;
} 