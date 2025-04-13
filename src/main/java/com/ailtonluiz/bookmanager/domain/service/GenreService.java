package com.ailtonluiz.bookmanager.domain.service;

import com.ailtonluiz.bookmanager.api.model.GenreInputModel;
import com.ailtonluiz.bookmanager.api.model.GenreModel;
import com.ailtonluiz.bookmanager.domain.exception.EntityInUseException;
import com.ailtonluiz.bookmanager.domain.exception.GenreNotFoundException;
import com.ailtonluiz.bookmanager.domain.model.Genre;
import com.ailtonluiz.bookmanager.domain.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio para gestionar géneros.
 * Este servicio proporciona métodos para operaciones CRUD de géneros.
 */
@Service
@AllArgsConstructor
public class GenreService {

    private static final String MESSAGE_ENTITY_IN_USE
            = "No se puede eliminar el Género con el código %d porque está en uso.";

    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    /**
     * Lista todos los géneros.
     *
     * @return Lista de géneros
     */
    public List<GenreModel> list() {
        return genreRepository.findAll().stream()
                .map(this::toModel)
                .toList();
    }

    /**
     * Busca un género por ID.
     *
     * @param id ID del género a buscar
     * @return Género encontrado
     */
    public GenreModel findById(Long id) {
        return toModel(findOrFail(id));
    }

    /**
     * Añade un nuevo género.
     *
     * @param genreInputModel Datos del género a crear
     * @return Género creado
     */
    @Transactional
    public GenreModel add(GenreInputModel genreInputModel) {
        Genre genre = new Genre();
        genre.setName(genreInputModel.getName());
        genre.setEnabled(true);

        return toModel(genreRepository.save(genre));
    }

    /**
     * Actualiza un género existente.
     *
     * @param id ID del género a actualizar
     * @param genreInputModel Datos actualizados del género
     * @return Género actualizado
     */
    @Transactional
    public GenreModel update(Long id, GenreInputModel genreInputModel) {
        Genre genre = findOrFail(id);
        genre.setName(genreInputModel.getName());
        return toModel(genreRepository.save(genre));
    }

    /**
     * Habilita un género.
     *
     * @param id ID del género a habilitar
     */
    @Transactional
    public void enabled(Long id) {
        Genre genre = findOrFail(id);
        genre.enabledGenre();
        genreRepository.save(genre);
    }

    /**
     * Deshabilita un género.
     *
     * @param id ID del género a deshabilitar
     */
    @Transactional
    public void disabled(Long id) {
        Genre genre = findOrFail(id);
        genre.disabledGenre();
        genreRepository.save(genre);
    }

    /**
     * Elimina un género.
     *
     * @param id ID del género a eliminar
     */
    @Transactional
    public void remove(Long id) {
        try {
            genreRepository.deleteById(id);
            genreRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new GenreNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MESSAGE_ENTITY_IN_USE, id));
        }
    }

    /**
     * Busca un género por ID o lanza una excepción si no se encuentra.
     *
     * @param id ID del género a buscar
     * @return Género encontrado
     */
    private Genre findOrFail(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
    }

    /**
     * Convierte un género a modelo.
     *
     * @param genre Género a convertir
     * @return Modelo del género
     */
    private GenreModel toModel(Genre genre) {
        return modelMapper.map(genre, GenreModel.class);
    }
} 