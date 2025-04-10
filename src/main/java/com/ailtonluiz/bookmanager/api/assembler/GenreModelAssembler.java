package com.ailtonluiz.bookmanager.api.assembler;

import com.ailtonluiz.bookmanager.api.model.GenreModel;
import com.ailtonluiz.bookmanager.model.domain.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GenreModel toModel(Genre genre) {
        return modelMapper.map(genre, GenreModel.class);
    }

    public List<GenreModel> toCollectionModel(List<Genre> genres) {
        return genres.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
