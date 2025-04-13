package com.ailtonluiz.bookmanager.domain.service;

import com.ailtonluiz.bookmanager.api.model.BookInputModel;
import com.ailtonluiz.bookmanager.api.model.BookModel;
import com.ailtonluiz.bookmanager.domain.exception.BookNotFoundException;
import com.ailtonluiz.bookmanager.domain.exception.EntityInUseException;
import com.ailtonluiz.bookmanager.domain.model.Author;
import com.ailtonluiz.bookmanager.domain.model.Book;
import com.ailtonluiz.bookmanager.domain.model.Genre;
import com.ailtonluiz.bookmanager.domain.repository.AuthorRepository;
import com.ailtonluiz.bookmanager.domain.repository.BookRepository;
import com.ailtonluiz.bookmanager.domain.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio para gestionar libros.
 * Este servicio proporciona métodos para operaciones CRUD de libros.
 */
@Service
@AllArgsConstructor
public class BookService {

    private static final String MESSAGE_ENTITY_IN_USE
            = "No se puede eliminar el Libro con el código %d porque está en uso.";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    /**
     * Lista todos los libros.
     *
     * @return Lista de libros
     */
    public List<BookModel> list() {
        return bookRepository.findAll().stream()
                .map(this::toModel)
                .toList();
    }

    /**
     * Busca un libro por ID.
     *
     * @param id ID del libro a buscar
     * @return Libro encontrado
     */
    public BookModel findById(Long id) {
        return toModel(findOrFail(id));
    }

    /**
     * Añade un nuevo libro.
     *
     * @param bookInputModel Datos del libro a crear
     * @return Libro creado
     */
    @Transactional
    public BookModel add(BookInputModel bookInputModel) {
        Book book = new Book();
        book.setTitle(bookInputModel.getTitle());
        book.setDescription(bookInputModel.getDescription());
        book.setYear(bookInputModel.getYear());
        book.setPhotoUrl(bookInputModel.getPhotoUrl());
        
        Author author = authorRepository.findById(bookInputModel.getAuthorId())
                .orElseThrow(() -> new BookNotFoundException("Autor no encontrado"));
        book.setAuthor(author);
        
        Genre genre = genreRepository.findById(bookInputModel.getGenreId())
                .orElseThrow(() -> new BookNotFoundException("Género no encontrado"));
        book.setGenre(genre);
        
        book.setEnabled(true);

        return toModel(bookRepository.save(book));
    }

    /**
     * Actualiza un libro existente.
     *
     * @param bookInputModel Datos actualizados del libro
     * @return Libro actualizado
     */
    @Transactional
    public BookModel update(BookInputModel bookInputModel) {
        Book book = findOrFail(bookInputModel.getId());

        book.setTitle(bookInputModel.getTitle());
        book.setDescription(bookInputModel.getDescription());
        book.setYear(bookInputModel.getYear());
        book.setPhotoUrl(bookInputModel.getPhotoUrl());
        
        Author author = authorRepository.findById(bookInputModel.getAuthorId())
                .orElseThrow(() -> new BookNotFoundException("Autor no encontrado"));
        book.setAuthor(author);
        
        Genre genre = genreRepository.findById(bookInputModel.getGenreId())
                .orElseThrow(() -> new BookNotFoundException("Género no encontrado"));
        book.setGenre(genre);

        return toModel(bookRepository.save(book));
    }

    /**
     * Habilita un libro.
     *
     * @param id ID del libro a habilitar
     */
    @Transactional
    public void enabled(Long id) {
        Book book = findOrFail(id);
        book.enabledBook();
        bookRepository.save(book);
    }

    /**
     * Deshabilita un libro.
     *
     * @param id ID del libro a deshabilitar
     */
    @Transactional
    public void disabled(Long id) {
        Book book = findOrFail(id);
        book.disabledBook();
        bookRepository.save(book);
    }

    /**
     * Elimina un libro.
     *
     * @param id ID del libro a eliminar
     */
    @Transactional
    public void remove(Long id) {
        try {
            bookRepository.deleteById(id);
            bookRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MESSAGE_ENTITY_IN_USE, id));
        }
    }

    /**
     * Busca un libro por ID o lanza una excepción si no se encuentra.
     *
     * @param id ID del libro a buscar
     * @return Libro encontrado
     */
    private Book findOrFail(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    /**
     * Convierte un libro a modelo.
     *
     * @param book Libro a convertir
     * @return Modelo del libro
     */
    private BookModel toModel(Book book) {
        return modelMapper.map(book, BookModel.class);
    }
} 