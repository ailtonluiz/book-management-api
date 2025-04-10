package com.ailtonluiz.bookmanager.domain.repository;

import com.ailtonluiz.bookmanager.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
