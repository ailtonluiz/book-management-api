package com.ailtonluiz.bookmanager.api.controller;

import com.ailtonluiz.bookmanager.api.assembler.GenreInputDisassembler;
import com.ailtonluiz.bookmanager.api.assembler.GenreModelAssembler;
import com.ailtonluiz.bookmanager.api.model.GenreModel;
import com.ailtonluiz.bookmanager.api.model.input.GenreInput;
import com.ailtonluiz.bookmanager.domain.model.Genre;
import com.ailtonluiz.bookmanager.domain.repository.GenreRepository;
import com.ailtonluiz.bookmanager.domain.service.RegisterGenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
@Tag(name = "Géneros", description = "API para gestión de géneros literarios")
public class GenreController {

    @Autowired
    private RegisterGenreService registerGenreService;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreModelAssembler genreModelAssembler;

    @Autowired
    private GenreInputDisassembler genreInputDisassembler;

    @Operation(
        summary = "Listar todos los géneros",
        description = "Este endpoint devuelve todos los géneros literarios actualmente registrados en la base de datos",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "La lista de géneros se ha recuperado correctamente",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Ejemplo de respuesta",
                        value = "[{\"id\":1, \"name\":\"Romance\", \"enabled\":true, \"createdAt\":\"2024-01-01T12:00:00Z\", \"updatedAt\":\"2024-01-10T15:30:00Z\"}, {\"id\":2, \"name\":\"Ficción Científica\", \"enabled\":true, \"createdAt\":\"2024-01-02T10:00:00Z\", \"updatedAt\":\"2024-01-11T14:20:00Z\"}]"
                    )
                )
            )
        }
    )
    @GetMapping
    public List<GenreModel> list() {
        List<Genre> genres = genreRepository.findAll();
        return genreModelAssembler.toCollectionModel(genres);
    }

    @Operation(
        summary = "Buscar género por ID",
        description = "Este endpoint devuelve un género específico por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "El género se ha encontrado correctamente",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Ejemplo de respuesta",
                        value = "{\"id\":1, \"name\":\"Romance\", \"enabled\":true, \"createdAt\":\"2024-01-01T12:00:00Z\", \"updatedAt\":\"2024-01-10T15:30:00Z\"}"
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Género no encontrado"
            )
        }
    )
    @GetMapping("/{genreId}")
    public GenreModel findById(@PathVariable Long genreId) {
        Genre genre = registerGenreService.findOrFail(genreId);
        return genreModelAssembler.toModel(genre);
    }

    @Operation(
        summary = "Añadir nuevo género",
        description = "Este endpoint crea un nuevo género literario en el sistema",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Género creado correctamente",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Ejemplo de respuesta",
                        value = "{\"id\":1, \"name\":\"Romance\", \"enabled\":true, \"createdAt\":\"2024-01-01T12:00:00Z\", \"updatedAt\":\"2024-01-10T15:30:00Z\"}"
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Datos inválidos proporcionados"
            )
        }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenreModel add(@RequestBody @Valid GenreInput genreInput) {
        Genre genre = genreInputDisassembler.toDomainObject(genreInput);
        genre = registerGenreService.save(genre);
        return genreModelAssembler.toModel(genre);
    }

    @Operation(
        summary = "Actualizar género existente",
        description = "Este endpoint actualiza un género existente por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Género actualizado correctamente",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Ejemplo de respuesta",
                        value = "{\"id\":1, \"name\":\"Romance\", \"enabled\":true, \"createdAt\":\"2024-01-01T12:00:00Z\", \"updatedAt\":\"2024-01-10T15:30:00Z\"}"
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Género no encontrado"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Datos inválidos proporcionados"
            )
        }
    )
    @PutMapping("/{genreId}")
    public GenreModel update(@PathVariable Long genreId, @RequestBody @Valid GenreInput genreInput) {
        Genre currentGenre = registerGenreService.findOrFail(genreId);
        genreInputDisassembler.copyToDomainObject(genreInput, currentGenre);
        currentGenre = registerGenreService.save(currentGenre);
        return genreModelAssembler.toModel(currentGenre);
    }

    @Operation(
        summary = "Habilitar género",
        description = "Este endpoint habilita un género específico por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Género habilitado correctamente"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Género no encontrado"
            )
        }
    )
    @PutMapping("/{genreId}/enable")
    public void enabled(@PathVariable Long genreId) {
        registerGenreService.enabled(genreId);
    }

    @Operation(
        summary = "Deshabilitar género",
        description = "Este endpoint deshabilita un género específico por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Género deshabilitado correctamente"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Género no encontrado"
            )
        }
    )
    @PutMapping("/{genreId}/disabled")
    public void disabled(@PathVariable Long genreId) {
        registerGenreService.disabled(genreId);
    }

    @Operation(
        summary = "Eliminar género",
        description = "Este endpoint elimina un género específico por su ID",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Género eliminado correctamente"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Género no encontrado"
            ),
            @ApiResponse(
                responseCode = "409",
                description = "Género en uso, no puede ser eliminado"
            )
        }
    )
    @DeleteMapping("/{genreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long genreId) {
        registerGenreService.delete(genreId);
    }
}
