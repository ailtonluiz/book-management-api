package com.ailtonluiz.bookmanager.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Modelo de resumen de una nacionalidad para la API.
 * Este modelo se utiliza para representar información resumida de una nacionalidad en otros modelos.
 */
@Getter
@Setter
public class NationalitySummaryModel {

    /**
     * Nombre de la nacionalidad
     */
    private String name;
}
