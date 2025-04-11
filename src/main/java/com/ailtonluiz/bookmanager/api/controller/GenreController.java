package com.ailtonluiz.bookmanager.api.controller;

import com.ailtonluiz.bookmanager.api.assembler.GenreInputDisassembler;
import com.ailtonluiz.bookmanager.api.assembler.GenreModelAssembler;
import com.ailtonluiz.bookmanager.api.model.GenreModel;
import com.ailtonluiz.bookmanager.api.model.input.GenreInput;
import com.ailtonluiz.bookmanager.domain.model.Genre;
import com.ailtonluiz.bookmanager.domain.repository.GenreRepository;
import com.ailtonluiz.bookmanager.domain.service.RegisterGenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {

    @Autowired
    private RegisterGenreService registerGenreService;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreModelAssembler genreModelAssembler;

    @Autowired
    private GenreInputDisassembler genreInputDisassembler;

    @GetMapping
    public List<GenreModel> list() {
        List<Genre> genres = genreRepository.findAll();
        return genreModelAssembler.toCollectionModel(genres);
    }

    @GetMapping("/{genreId}")
    public GenreModel findById(@PathVariable Long genreId) {
        Genre genre = registerGenreService.findOrFail(genreId);
        return genreModelAssembler.toModel(genre);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenreModel add(@RequestBody @Valid GenreInput genreInput) {
        Genre genre = genreInputDisassembler.toDomainObject(genreInput);
        genre = registerGenreService.save(genre);
        return genreModelAssembler.toModel(genre);
    }

    @PutMapping("/{genreId}")
    public GenreModel update(@PathVariable Long genreId, @RequestBody @Valid GenreInput genreInput) {
        Genre currentGenre = registerGenreService.findOrFail(genreId);
        genreInputDisassembler.copyToDomainObject(genreInput, currentGenre);
        currentGenre = registerGenreService.save(currentGenre);

        return genreModelAssembler.toModel(currentGenre);
    }

    @PutMapping("/{genreId}/enable")
    public void enabled(@PathVariable Long genreId) {
        registerGenreService.enabled(genreId);
    }

    @PutMapping("/{genreId}/disabled")
    public void disabled(@PathVariable Long genreId) {
        registerGenreService.disabled(genreId);
    }

    @DeleteMapping("/{genreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long genreId) {
        registerGenreService.delete(genreId);
    }


}
