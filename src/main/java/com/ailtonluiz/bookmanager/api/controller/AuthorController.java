package com.ailtonluiz.bookmanager.api.controller;

import com.ailtonluiz.bookmanager.api.assembler.AuthorInputDisassembler;
import com.ailtonluiz.bookmanager.api.assembler.AuthorModelAssembler;
import com.ailtonluiz.bookmanager.api.model.AuthorModel;
import com.ailtonluiz.bookmanager.api.model.input.AuthorInput;
import com.ailtonluiz.bookmanager.domain.exception.NationalityNotFoundException;
import com.ailtonluiz.bookmanager.domain.exception.TransactionException;
import com.ailtonluiz.bookmanager.domain.model.Author;
import com.ailtonluiz.bookmanager.domain.repository.AuthorRepository;
import com.ailtonluiz.bookmanager.domain.service.RegisterAuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private RegisterAuthorService registerAuthorService;

    @Autowired
    private AuthorModelAssembler authorModelAssembler;

    @Autowired
    private AuthorInputDisassembler authorInputDisassembler;


    @GetMapping
    public List<AuthorModel> list() {
        List<Author> authors = authorRepository.findAll();

        return authorModelAssembler.toCollectionModel(authors);
    }

    @GetMapping("/{authorId}")
    public AuthorModel findById(@PathVariable Long authorId) {
        Author author = registerAuthorService.findOrFail(authorId);
        return authorModelAssembler.toModel(author);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorModel add(@RequestBody @Valid AuthorInput authorInput) {
        try {
            Author author = authorInputDisassembler.toDomainObject(authorInput);

            author = registerAuthorService.save(author);

            return authorModelAssembler.toModel(author);
        } catch (NationalityNotFoundException e) {
            throw new TransactionException(e.getMessage(), e);
        }
    }

    @PutMapping("/{authorId}")
    public AuthorModel update(@PathVariable Long authorId, @RequestBody @Valid AuthorInput authorInput) {
        try {
            Author author = authorInputDisassembler.toDomainObject(authorInput);

            author = registerAuthorService.save(author);

            return authorModelAssembler.toModel(author);
        } catch (NationalityNotFoundException e) {
            throw new TransactionException(e.getMessage(), e);
        }
    }

    @PutMapping("/{authorId}/enable")
    public void enabled(@PathVariable Long authorId) {
        registerAuthorService.enabled(authorId);
    }


    @PutMapping("/{authorId}/disable")
    public void disabled(@PathVariable Long authorId) {
        registerAuthorService.disabled(authorId);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long authorId) {
        registerAuthorService.delete(authorId);
    }


}
