package com.ailtonluiz.bookmanager.domain.service;

import com.ailtonluiz.bookmanager.domain.exception.AuthorNotFoundException;
import com.ailtonluiz.bookmanager.domain.exception.BookNotFoundException;
import com.ailtonluiz.bookmanager.domain.exception.EntityInUseException;
import com.ailtonluiz.bookmanager.domain.model.Author;
import com.ailtonluiz.bookmanager.domain.model.Book;
import com.ailtonluiz.bookmanager.domain.model.Genre;
import com.ailtonluiz.bookmanager.domain.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegisterBookService {

    private static final String MESSAGE_ENTITY_IN_USE
            = "No se puede eliminar el Libro con el código %d porque está en uso.";


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RegisterAuthorService registerAuthorService;

    @Autowired
    private RegisterGenreService registerGenreService;


    @Transactional

    public Book save(Book book) {

        Long authorId = book.getAuthor().getId();
        Long genreId = book.getGenre().getId();
        Author author = registerAuthorService.findOrFail(authorId);
        Genre genre = registerGenreService.findOrFail(genreId);

        book.setAuthor(author);
        book.setGenre(genre);
        return bookRepository.save(book);

    }

    @Transactional
    public void delete(Long bookId) {

        try {
            bookRepository.deleteById(bookId);
            bookRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundException(bookId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MESSAGE_ENTITY_IN_USE, bookId));


        }

    }

    public Book findOrFail(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new AuthorNotFoundException(bookId));

    }
}