package com.ailtonluiz.bookmanager.domain.model;

import com.ailtonluiz.bookmanager.config.validation.ValidBookYear;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "Por favor, ingrese el título del libro. Este campo es obligatorio.")
    private String title;

    @NotBlank(message = "Por favor, ingrese la descripción. Este campo es obligatorio.")
    private String description;

    @Column(length = 13)
    private String isbn;

    @NotNull
    @ValidBookYear
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private String photoUrl;

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

        title = title.toUpperCase();
        description = description.toUpperCase();
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
