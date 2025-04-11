package com.ailtonluiz.bookmanager.domain.service;

import com.ailtonluiz.bookmanager.domain.exception.EntityInUseException;
import com.ailtonluiz.bookmanager.domain.exception.GenreNotFoundException;
import com.ailtonluiz.bookmanager.domain.model.Genre;
import com.ailtonluiz.bookmanager.domain.repository.GenreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegisterGenreService {

    private static final String MESSAGE_ENTITY_IN_USE
            = "No se puede eliminar el genero con el código %d porque está en uso.";

    @Autowired
    private GenreRepository genreRepository;

    @Transactional
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional
    public void enabled(Long genreId) {
        Genre currenteGenre = findOrFail(genreId);
        currenteGenre.enabledGenre();

    }

    @Transactional
    public void disabled(Long genreId) {
        Genre currentGenre = findOrFail(genreId);
        currentGenre.disabledGenre();
    }

    @Transactional
    public void delete(Long genreId) {
        try {
            genreRepository.deleteById(genreId);
            genreRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new GenreNotFoundException(genreId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MESSAGE_ENTITY_IN_USE, genreId));
        }
    }

    public Genre findOrFail(Long genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));
    }

}
