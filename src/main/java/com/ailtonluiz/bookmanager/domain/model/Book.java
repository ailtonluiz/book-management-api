package com.ailtonluiz.bookmanager.domain.model;

import com.ailtonluiz.bookmanager.core.config.validation.ValidBookYear;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

/**
 * Entidad que representa un libro.
 * Esta clase contiene la información básica de un libro.
 */
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

    /**
     * Identificador único del libro
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Título del libro
     */
    @NotBlank(message = "Por favor, ingrese el título del libro. Este campo es obligatorio.")
    @Size(min = 3, max = 150, message = "El título debe tener entre 3 y 150 caracteres")
    private String title;

    /**
     * Descripción o resumen del libro
     */
    @NotBlank(message = "Por favor, ingrese la descripción del libro. Este campo es obligatorio.")
    @Size(min = 10, max = 1000, message = "La descripción debe tener entre 10 y 1000 caracteres")
    private String description;

    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", 
            message = "ISBN inválido. Utilice el formato ISBN-10 o ISBN-13")
    @Column(length = 13, unique = true)
    private String isbn;

    /**
     * Año de publicación del libro
     */
    @NotNull(message = "Por favor, ingrese el año de publicación. Este campo es obligatorio.")
    @ValidBookYear
    private Integer year;

    /**
     * Género del libro
     */
    @NotNull(message = "Por favor, seleccione el género del libro. Este campo es obligatorio.")
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    /**
     * Autor del libro
     */
    @NotNull(message = "Por favor, seleccione el autor del libro. Este campo es obligatorio.")
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    /**
     * URL de la portada del libro
     */
    @Pattern(regexp = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?$", 
            message = "URL de la foto inválida")
    private String photoUrl;

    /**
     * Estado de habilitación del libro
     */
    private Boolean enabled = Boolean.TRUE;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updatedAt;

    @PrePersist
    @PreUpdate
    private void prePersistUpdate() {
        if (title != null) {
            title = title.toUpperCase();
        }
        if (description != null) {
            description = description.toUpperCase();
        }
    }

    /**
     * Activa el libro, estableciendo el campo 'enabled' como verdadero.
     * Este método puede ser llamado para activar el libro a través de la API.
     */
    public void enabledBook() {
        this.enabled = Boolean.TRUE;
    }

    /**
     * Desactiva el libro, estableciendo el campo 'enabled' como falso.
     * Este método puede ser llamado para desactivar el libro a través de la API.
     */
    public void disabledBook() {
        this.enabled = Boolean.FALSE;
    }

}
