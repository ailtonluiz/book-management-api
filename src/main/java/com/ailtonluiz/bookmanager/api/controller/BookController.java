package com.ailtonluiz.bookmanager.api.controller;

import com.ailtonluiz.bookmanager.api.assembler.BookInputDisassembler;
import com.ailtonluiz.bookmanager.api.assembler.BookModelAssembler;
import com.ailtonluiz.bookmanager.api.model.BookModel;
import com.ailtonluiz.bookmanager.api.model.input.BookInput;
import com.ailtonluiz.bookmanager.domain.exception.AuthorNotFoundException;
import com.ailtonluiz.bookmanager.domain.exception.GenreNotFoundException;
import com.ailtonluiz.bookmanager.domain.exception.TransactionException;
import com.ailtonluiz.bookmanager.domain.model.Book;
import com.ailtonluiz.bookmanager.domain.repository.BookRepository;
import com.ailtonluiz.bookmanager.domain.service.RegisterBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RegisterBookService registerBookService;

    @Autowired
    private BookModelAssembler bookModelAssembler;

    @Autowired
    private BookInputDisassembler bookInputDisassembler;


    @GetMapping
    public List<BookModel> list() {
        List<Book> books = bookRepository.findAll();
        return bookModelAssembler.toCollectionModel(books);
    }


    @GetMapping("/{bookId}")
    public BookModel findById(@PathVariable Long bookId) {
        Book book = registerBookService.findOrFail(bookId);
        return bookModelAssembler.toModel(book);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookModel add(@RequestBody @Valid BookInput bookInput) {
        try {
            Book book = bookInputDisassembler.toDomainObject(bookInput);

            book = registerBookService.save(book);

            return bookModelAssembler.toModel(book);
        } catch (AuthorNotFoundException | GenreNotFoundException e) {
            throw new TransactionException(e.getMessage(), e);
        }
    }

    @PutMapping("/{bookId}")
    public BookModel update(@PathVariable Long bookId, @RequestBody @Valid BookInput bookInput) {
        try {
            Book book = bookInputDisassembler.toDomainObject(bookInput);

            book = registerBookService.save(book);

            return bookModelAssembler.toModel(book);
        } catch (AuthorNotFoundException | GenreNotFoundException e) {
            throw new TransactionException(e.getMessage(), e);
        }
    }

    @PutMapping("/{bookId}/enable")
    public void enabled(@PathVariable Long bookId) {
        registerBookService.enabled(bookId);
    }


    @PutMapping("/{bookId}/disable")
    public void disabled(@PathVariable Long bookId) {
        registerBookService.disabled(bookId);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long bookId) {
        registerBookService.delete(bookId);
    }


}
