package com.ailtonluiz.bookmanager.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false)
    private String copyCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CopyStatus status;

    private LocalDate acquisitionDate;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;




}
