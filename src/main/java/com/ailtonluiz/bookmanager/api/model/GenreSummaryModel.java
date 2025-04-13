package com.ailtonluiz.bookmanager.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Modelo de resumen de un género para la API.
 * Este modelo se utiliza para representar información resumida de un género en otros modelos.
 */
@Getter
@Setter
public class GenreSummaryModel {

    /**
     * Nombre del género
     */
    private String name;
}
