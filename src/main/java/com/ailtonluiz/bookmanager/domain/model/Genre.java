package com.ailtonluiz.bookmanager.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

/**
 * Entidad que representa un género literario.
 * Esta clase contiene la información básica de un género.
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Genre {

    /**
     * Identificador único del género
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Nombre del género
     */
    @NotBlank(message = "No puede ser nulo o vazio")
    private String name;

    /**
     * Estado de habilitación del género
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

        name = name.toUpperCase();
    }

    /**
     * Activa el genero, estableciendo el campo 'enabled' como verdadero.
     * Este método puede ser llamado para activar el genero a través de la API.
     */
    public void enabledGenre() {
        this.enabled = Boolean.TRUE;
    }

    /**
     * Desactiva el genero, estableciendo el campo 'enabled' como falso.
     * Este método puede ser llamado para desactivar el genero a través de la API.
     */
    public void disabledGenre() {
        this.enabled = Boolean.FALSE;
    }

}
