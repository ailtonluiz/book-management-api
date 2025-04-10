package com.ailtonluiz.bookmanager.api.assembler;

import com.ailtonluiz.bookmanager.api.model.BookModel;
import com.ailtonluiz.bookmanager.domain.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public BookModel toModel(Book book) {
        return modelMapper.map(book, BookModel.class);
    }

    public List<BookModel> toCollectionModel(List<Book> books) {
        return books.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
