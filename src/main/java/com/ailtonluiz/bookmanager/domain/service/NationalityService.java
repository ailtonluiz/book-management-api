package com.ailtonluiz.bookmanager.domain.service;

import com.ailtonluiz.bookmanager.api.model.NationalityInputModel;
import com.ailtonluiz.bookmanager.api.model.NationalityModel;
import com.ailtonluiz.bookmanager.domain.exception.EntityInUseException;
import com.ailtonluiz.bookmanager.domain.exception.NationalityNotFoundException;
import com.ailtonluiz.bookmanager.domain.model.Nationality;
import com.ailtonluiz.bookmanager.domain.repository.NationalityRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio para gestionar nacionalidades.
 * Este servicio proporciona métodos para operaciones CRUD de nacionalidades.
 */
@Service
@AllArgsConstructor
public class NationalityService {

    private static final String MESSAGE_ENTITY_IN_USE
            = "No se puede eliminar la Nacionalidad con el código %d porque está en uso.";

    private final NationalityRepository nationalityRepository;
    private final ModelMapper modelMapper;

    /**
     * Lista todas las nacionalidades.
     *
     * @return Lista de nacionalidades
     */
    public List<NationalityModel> list() {
        return nationalityRepository.findAll().stream()
                .map(this::toModel)
                .toList();
    }

    /**
     * Busca una nacionalidad por ID.
     *
     * @param id ID de la nacionalidad a buscar
     * @return Nacionalidad encontrada
     */
    public NationalityModel findById(Long id) {
        return toModel(findOrFail(id));
    }

    /**
     * Añade una nueva nacionalidad.
     *
     * @param nationalityInputModel Datos de la nacionalidad a crear
     * @return Nacionalidad creada
     */
    @Transactional
    public NationalityModel add(NationalityInputModel nationalityInputModel) {
        Nationality nationality = new Nationality();
        nationality.setName(nationalityInputModel.getName());
        nationality.setEnabled(true);

        return toModel(nationalityRepository.save(nationality));
    }

    /**
     * Actualiza una nacionalidad existente.
     *
     * @param id ID de la nacionalidad a actualizar
     * @param nationalityInputModel Datos actualizados de la nacionalidad
     * @return Nacionalidad actualizada
     */
    @Transactional
    public NationalityModel update(Long id, NationalityInputModel nationalityInputModel) {
        Nationality nationality = findOrFail(id);
        nationality.setName(nationalityInputModel.getName());
        return toModel(nationalityRepository.save(nationality));
    }

    /**
     * Habilita una nacionalidad.
     *
     * @param id ID de la nacionalidad a habilitar
     */
    @Transactional
    public void enabled(Long id) {
        Nationality nationality = findOrFail(id);
        nationality.enabledNationality();
        nationalityRepository.save(nationality);
    }

    /**
     * Deshabilita una nacionalidad.
     *
     * @param id ID de la nacionalidad a deshabilitar
     */
    @Transactional
    public void disabled(Long id) {
        Nationality nationality = findOrFail(id);
        nationality.disabledNationality();
        nationalityRepository.save(nationality);
    }

    /**
     * Elimina una nacionalidad.
     *
     * @param id ID de la nacionalidad a eliminar
     */
    @Transactional
    public void remove(Long id) {
        try {
            nationalityRepository.deleteById(id);
            nationalityRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new NationalityNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MESSAGE_ENTITY_IN_USE, id));
        }
    }

    /**
     * Busca una nacionalidad por ID o lanza una excepción si no se encuentra.
     *
     * @param id ID de la nacionalidad a buscar
     * @return Nacionalidad encontrada
     */
    private Nationality findOrFail(Long id) {
        return nationalityRepository.findById(id)
                .orElseThrow(() -> new NationalityNotFoundException(id));
    }

    /**
     * Convierte una nacionalidad a modelo.
     *
     * @param nationality Nacionalidad a convertir
     * @return Modelo de la nacionalidad
     */
    private NationalityModel toModel(Nationality nationality) {
        return modelMapper.map(nationality, NationalityModel.class);
    }
} 