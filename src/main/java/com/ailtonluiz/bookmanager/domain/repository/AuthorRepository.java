package com.ailtonluiz.bookmanager.domain.repository;

import com.ailtonluiz.bookmanager.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gestionar autores.
 * Este repositorio proporciona métodos para operaciones CRUD de autores.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
