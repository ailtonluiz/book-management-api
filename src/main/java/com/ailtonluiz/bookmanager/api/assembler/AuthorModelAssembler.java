package com.ailtonluiz.bookmanager.api.assembler;

import com.ailtonluiz.bookmanager.api.model.AuthorModel;
import com.ailtonluiz.bookmanager.model.domain.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public AuthorModel toModel(Author author) {
        return modelMapper.map(author, AuthorModel.class);
    }

    public List<AuthorModel> toCollectionModel(List<Author> authors) {
        return authors.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
