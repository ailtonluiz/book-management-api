package com.ailtonluiz.bookmanager.domain.service;

import com.ailtonluiz.bookmanager.domain.exception.AuthorNotFoundException;
import com.ailtonluiz.bookmanager.domain.exception.EntityInUseException;
import com.ailtonluiz.bookmanager.domain.model.Author;
import com.ailtonluiz.bookmanager.domain.model.Nationality;
import com.ailtonluiz.bookmanager.domain.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegisterAuthorService {

    private static final String MESSAGE_ENTITY_IN_USE
            = "No se puede eliminar el Autor con el código %d porque está en uso.";


    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private RegisterNationalityService registerNationalityService;

    @Transactional
    public Author save(Author author) {

        Long nationalityId = author.getNationality().getId();
        Nationality nationality = registerNationalityService.findOrFail(nationalityId);

        author.setNationality(nationality);
        return authorRepository.save(author);

    }

    @Transactional
    public void delete(Long authorId) {

        try {
            authorRepository.deleteById(authorId);
            authorRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AuthorNotFoundException(authorId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MESSAGE_ENTITY_IN_USE, authorId));


        }

    }

    public Author findOrFail(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

    }
}