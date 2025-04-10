package com.ailtonluiz.bookmanager.api.assembler;

import com.ailtonluiz.bookmanager.api.model.input.BookInput;
import com.ailtonluiz.bookmanager.domain.model.Author;
import com.ailtonluiz.bookmanager.domain.model.Book;
import com.ailtonluiz.bookmanager.domain.model.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Book toDomainObject(BookInput bookInput) {
        return modelMapper.map(bookInput, Book.class);
    }

    public void copyToDomainObject(BookInput bookInput, Book book) {
        book.setAuthor(new Author());
        book.setGenre(new Genre());
        modelMapper.map(bookInput, book);
    }
}
