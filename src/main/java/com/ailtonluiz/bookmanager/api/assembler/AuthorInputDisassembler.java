package com.ailtonluiz.bookmanager.api.assembler;

import com.ailtonluiz.bookmanager.api.model.input.AuthorInput;
import com.ailtonluiz.bookmanager.domain.model.Author;
import com.ailtonluiz.bookmanager.domain.model.Nationality;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Author toDomainObject(AuthorInput authorInput) {
        return modelMapper.map(authorInput, Author.class);
    }

    public void copyToDomainObject(AuthorInput authorInput, Author author) {
        author.setNationality(new Nationality());
        modelMapper.map(authorInput, author);
    }
}
