package com.ailtonluiz.bookmanager.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

/**
 * Entidad que representa un autor.
 * Esta clase contiene la información básica de un autor.
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Author {

    /**
     * Identificador único del autor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Nombre del autor
     */
    @NotBlank(message = "No puede ser nulo o vazio")
    private String name;

    /**
     * Nacionalidad del autor
     */
    @ManyToOne
    @JoinColumn(name = "nationality_id", nullable = false)
    private Nationality nationality;

    /**
     * Estado de habilitación del autor
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
     * Activa el autor, estableciendo el campo 'enabled' como verdadero.
     * Este método puede ser llamado para activar el autor a través de la API.
     */
    public void enabledAuthor() {
        this.enabled = Boolean.TRUE;
    }

    /**
     * Desactiva el autor, estableciendo el campo 'enabled' como falso.
     * Este método puede ser llamado para desactivar el autor a través de la API.
     */
    public void disabledAuthor() {
        this.enabled = Boolean.FALSE;
    }

}
