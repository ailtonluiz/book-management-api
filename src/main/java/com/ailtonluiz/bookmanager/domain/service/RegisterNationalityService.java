package com.ailtonluiz.bookmanager.domain.service;

import com.ailtonluiz.bookmanager.domain.exception.EntityInUseException;
import com.ailtonluiz.bookmanager.domain.exception.NationalityNotFoundException;
import com.ailtonluiz.bookmanager.domain.model.Nationality;
import com.ailtonluiz.bookmanager.domain.repository.NationalityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegisterNationalityService {

    private static final String MESSAGE_ENTITY_IN_USE
            = "No se puede eliminar la nacionalidad con el código %d porque está en uso.";

    @Autowired
    private NationalityRepository nationalityRepository;

    @Transactional
    public Nationality save(Nationality nationality) {
        return nationalityRepository.save(nationality);
    }

    @Transactional
    public void delete(Long nationalityId) {
        try {
            nationalityRepository.deleteById(nationalityId);
            nationalityRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new NationalityNotFoundException(nationalityId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MESSAGE_ENTITY_IN_USE, nationalityId));
        }
    }

    public Nationality findOrFail(Long nationalityId) {
        return nationalityRepository.findById(nationalityId)
                .orElseThrow(() -> new NationalityNotFoundException(nationalityId));
    }

}
