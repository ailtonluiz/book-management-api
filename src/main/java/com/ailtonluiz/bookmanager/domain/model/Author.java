package com.ailtonluiz.bookmanager.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "El nombre del autor no puede ser nulo o vazio")
    private String name;

    private Boolean enabled = Boolean.TRUE;

    @ManyToOne
    @JoinColumn(name = "nationality_id")
    private Nationality nationality;


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
     * Activa el Autor, estableciendo el campo 'enabled' como verdadero.
     * Este método puede ser llamado para activar el autor a través de la API.
     */
    public void enabledAuthor() {
        this.enabled = Boolean.TRUE;
    }

    /**
     * Desactiva el Autor, estableciendo el campo 'enabled' como falso.
     * Este método puede ser llamado para desactivar el autor a través de la API.
     */
    public void disabledAuthor() {
        this.enabled = Boolean.FALSE;
    }
}
