package com.ailtonluiz.bookmanager.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

/**
 * Entidad que representa una nacionalidad.
 * Esta clase contiene la información básica de una nacionalidad.
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Nationality {

    /**
     * Identificador único de la nacionalidad
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Nombre de la nacionalidad
     */
    @NotBlank(message = "No puede ser nulo o vazio")
    private String name;

    /**
     * Estado de habilitación de la nacionalidad
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
     * Activa la nacionalidad, estableciendo el campo 'enabled' como verdadero.
     * Este método puede ser llamado para activar la nacionalidad a través de la API.
     */
    public void enabledNationality() {
        this.enabled = Boolean.TRUE;
    }

    /**
     * Desactiva la nacionalidad, estableciendo el campo 'enabled' como falso.
     * Este método puede ser llamado para desactivar la nacionalidad a través de la API.
     */
    public void disabledNationality() {
        this.enabled = Boolean.FALSE;
    }
}
