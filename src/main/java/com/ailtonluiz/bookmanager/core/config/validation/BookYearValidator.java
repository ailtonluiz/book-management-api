package com.ailtonluiz.bookmanager.core.config.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

/**
 * Validador para el año de publicación de un libro.
 * <p>
 * Este validador asegura que el año proporcionado esté entre 1450 d.C., año de la invención de la imprenta
 * por Johannes Gutenberg en Europa, y el año actual.
 * <p>
 * La imprenta de tipos móviles de Gutenberg en 1450 revolucionó la producción de libros en Occidente,
 * permitiendo la difusión masiva del conocimiento.
 *
 * @see <a href="https://es.wikipedia.org/wiki/Historia_del_libro">Historia de los libros</a>
 * @see <a href="https://es.wikipedia.org/wiki/Biblia_de_Gutenberg">Biblia de Gutenberg</a>
 */
public class BookYearValidator implements ConstraintValidator<ValidBookYear, Integer> {

    private static final int MIN_YEAR = 1450;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        int currentYear = Year.now().getValue();
        return value >= MIN_YEAR && value <= currentYear;
    }
}
