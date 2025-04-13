package com.ailtonluiz.bookmanager.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

/**
 * Modelo de representación de una nacionalidad para la API.
 * Este modelo se utiliza para transferir datos de nacionalidades entre el cliente y el servidor.
 */
@Getter
@Setter
public class NationalityModel {

    /**
     * Identificador único de la nacionalidad
     */
    private Long id;

    /**
     * Nombre de la nacionalidad
     */
    private String name;

    /**
     * Indica si la nacionalidad está activa en el sistema
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
