package com.ailtonluiz.bookmanager.domain.repository;

import com.ailtonluiz.bookmanager.domain.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
