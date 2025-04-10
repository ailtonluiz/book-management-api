package com.ailtonluiz.bookmanager.api.assembler;

import com.ailtonluiz.bookmanager.api.model.input.NationalityInput;
import com.ailtonluiz.bookmanager.model.domain.Nationality;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NationalityInputDisassembler {


    @Autowired
    private ModelMapper modelMapper;


    public Nationality toDomainObject(NationalityInput nationalityInput) {
        return modelMapper.map(nationalityInput, Nationality.class);
    }

    public void copyToDomainObject(NationalityInput nationalityInput, Nationality nationality) {
        modelMapper.map(nationalityInput, nationality);
    }
}
