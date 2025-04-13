package com.ailtonluiz.bookmanager.domain.service;

import com.ailtonluiz.bookmanager.api.model.AuthorInputModel;
import com.ailtonluiz.bookmanager.api.model.AuthorModel;
import com.ailtonluiz.bookmanager.domain.exception.AuthorNotFoundException;
import com.ailtonluiz.bookmanager.domain.exception.EntityInUseException;
import com.ailtonluiz.bookmanager.domain.model.Author;
import com.ailtonluiz.bookmanager.domain.model.Nationality;
import com.ailtonluiz.bookmanager.domain.repository.AuthorRepository;
import com.ailtonluiz.bookmanager.domain.repository.NationalityRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio para gestionar autores.
 * Este servicio proporciona métodos para operaciones CRUD de autores.
 */
@Service
@AllArgsConstructor
public class AuthorService {

    private static final String MESSAGE_ENTITY_IN_USE
            = "No se puede eliminar el Autor con el código %d porque está en uso.";

    private final AuthorRepository authorRepository;
    private final NationalityRepository nationalityRepository;
    private final ModelMapper modelMapper;

    /**
     * Lista todos los autores.
     *
     * @return Lista de autores
     */
    public List<AuthorModel> list() {
        return authorRepository.findAll().stream()
                .map(this::toModel)
                .toList();
    }

    /**
     * Busca un autor por ID.
     *
     * @param id ID del autor a buscar
     * @return Autor encontrado
     */
    public AuthorModel findById(Long id) {
        return toModel(findOrFail(id));
    }

    /**
     * Añade un nuevo autor.
     *
     * @param authorInputModel Datos del autor a crear
     * @return Autor creado
     */
    @Transactional
    public AuthorModel add(AuthorInputModel authorInputModel) {
        Author author = new Author();
        author.setName(authorInputModel.getName());
        
        Nationality nationality = nationalityRepository.findById(authorInputModel.getNationalityId())
                .orElseThrow(() -> new AuthorNotFoundException("Nacionalidad no encontrada"));
        author.setNationality(nationality);
        
        author.setEnabled(true);

        return toModel(authorRepository.save(author));
    }

    /**
     * Actualiza un autor existente.
     *
     * @param id ID del autor a actualizar
     * @param authorInputModel Datos actualizados del autor
     * @return Autor actualizado
     */
    @Transactional
    public AuthorModel update(Long id, AuthorInputModel authorInputModel) {
        Author author = findOrFail(id);

        author.setName(authorInputModel.getName());
        
        Nationality nationality = nationalityRepository.findById(authorInputModel.getNationalityId())
                .orElseThrow(() -> new AuthorNotFoundException("Nacionalidad no encontrada"));
        author.setNationality(nationality);

        return toModel(authorRepository.save(author));
    }

    /**
     * Habilita un autor.
     *
     * @param id ID del autor a habilitar
     */
    @Transactional
    public void enabled(Long id) {
        Author author = findOrFail(id);
        author.enabledAuthor();
        authorRepository.save(author);
    }

    /**
     * Deshabilita un autor.
     *
     * @param id ID del autor a deshabilitar
     */
    @Transactional
    public void disabled(Long id) {
        Author author = findOrFail(id);
        author.disabledAuthor();
        authorRepository.save(author);
    }

    /**
     * Elimina un autor.
     *
     * @param id ID del autor a eliminar
     */
    @Transactional
    public void remove(Long id) {
        try {
            authorRepository.deleteById(id);
            authorRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AuthorNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MESSAGE_ENTITY_IN_USE, id));
        }
    }

    /**
     * Busca un autor por ID o lanza una excepción si no se encuentra.
     *
     * @param id ID del autor a buscar
     * @return Autor encontrado
     */
    private Author findOrFail(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    /**
     * Convierte un autor a modelo.
     *
     * @param author Autor a convertir
     * @return Modelo del autor
     */
    private AuthorModel toModel(Author author) {
        return modelMapper.map(author, AuthorModel.class);
    }
} 