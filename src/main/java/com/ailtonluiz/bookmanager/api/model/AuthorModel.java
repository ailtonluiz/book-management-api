package com.ailtonluiz.bookmanager.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

/**
 * Modelo de representación de un autor para la API.
 * Este modelo se utiliza para transferir datos de autores entre el cliente y el servidor.
 */
@Getter
@Setter
public class AuthorModel {

    /**
     * Identificador único del autor
     */
    private Long id;

    /**
     * Nombre del autor
     */
    private String name;

    /**
     * Indica si el autor está activo en el sistema
     */
    private Boolean enabled;

    /**
     * Información resumida de la nacionalidad del autor
     */
    private NationalitySummaryModel nationality;

    /**
     * Fecha y hora de creación del registro
     */
    private OffsetDateTime createdAt;

    /**
     * Fecha y hora de la última actualización del registro
     */
    private OffsetDateTime updatedAt;

}
