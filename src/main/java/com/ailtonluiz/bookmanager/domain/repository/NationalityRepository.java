package com.ailtonluiz.bookmanager.domain.repository;

import com.ailtonluiz.bookmanager.domain.model.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Long> {
}
