package com.ailtonluiz.bookmanager.api.assembler;

import com.ailtonluiz.bookmanager.api.model.input.GenreInput;
import com.ailtonluiz.bookmanager.model.domain.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenreInputDisassembler {


    @Autowired
    private ModelMapper modelMapper;


    public Genre toDomainObject(GenreInput genreInput) {
        return modelMapper.map(genreInput, Genre.class);
    }

    public void copyToDomainObject(GenreInput genreInput, Genre genre) {
        modelMapper.map(genreInput, genre);
    }
}
