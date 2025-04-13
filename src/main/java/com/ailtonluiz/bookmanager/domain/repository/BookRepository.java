package com.ailtonluiz.bookmanager.domain.repository;

import com.ailtonluiz.bookmanager.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gestionar libros.
 * Este repositorio proporciona métodos para operaciones CRUD de libros.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
