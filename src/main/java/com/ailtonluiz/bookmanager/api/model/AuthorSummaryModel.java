package com.ailtonluiz.bookmanager.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Modelo de resumen de un autor para la API.
 * Este modelo se utiliza para representar información resumida de un autor en otros modelos.
 */
@Getter
@Setter
public class AuthorSummaryModel {

    /**
     * Nombre del autor
     */
    private String name;

}
