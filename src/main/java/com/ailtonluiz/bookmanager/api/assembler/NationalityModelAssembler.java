package com.ailtonluiz.bookmanager.api.assembler;

import com.ailtonluiz.bookmanager.api.model.NationalityModel;
import com.ailtonluiz.bookmanager.domain.model.Nationality;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NationalityModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public NationalityModel toModel(Nationality nationality) {
        return modelMapper.map(nationality, NationalityModel.class);
    }

    public List<NationalityModel> toCollectionModel(List<Nationality> nationalities) {
        return nationalities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
