package com.ailtonluiz.bookmanager.api.controller;

import com.ailtonluiz.bookmanager.api.assembler.AuthorInputDisassembler;
import com.ailtonluiz.bookmanager.api.assembler.AuthorModelAssembler;
import com.ailtonluiz.bookmanager.api.model.AuthorInputModel;
import com.ailtonluiz.bookmanager.api.model.AuthorModel;
import com.ailtonluiz.bookmanager.domain.exception.NationalityNotFoundException;
import com.ailtonluiz.bookmanager.domain.exception.TransactionException;
import com.ailtonluiz.bookmanager.domain.model.Author;
import com.ailtonluiz.bookmanager.domain.repository.AuthorRepository;
import com.ailtonluiz.bookmanager.domain.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar autores.
 * Este controlador proporciona endpoints para operaciones CRUD de autores.
 */
@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
@Tag(name = "Autores", description = "API para gestión de autores")
public class AuthorController {

    private final AuthorService authorService;

    /**
     * Lista todos los autores.
     *
     * @return Lista de autores
     */
    @GetMapping
    @Operation(summary = "Listar todos los autores", description = "Este endpoint devuelve todos los autores actualmente registrados en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de autores recuperada con éxito")
    })
    public ResponseEntity<List<AuthorModel>> list() {
        return ResponseEntity.ok(authorService.list());
    }

    /**
     * Busca un autor por ID.
     *
     * @param id ID del autor a buscar
     * @return Autor encontrado
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar autor por ID", description = "Este endpoint devuelve un autor específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor encontrado"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado")
    })
    public ResponseEntity<AuthorModel> findById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    /**
     * Añade un nuevo autor.
     *
     * @param authorInputModel Datos del autor a crear
     * @return Autor creado
     */
    @PostMapping
    @Operation(summary = "Añadir nuevo autor", description = "Este endpoint crea un nuevo autor en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autor creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de autor inválidos")
    })
    public ResponseEntity<AuthorModel> add(@RequestBody AuthorInputModel authorInputModel) {
        return ResponseEntity.ok(authorService.add(authorInputModel));
    }

    /**
     * Actualiza un autor existente.
     *
     * @param id ID del autor a actualizar
     * @param authorInputModel Datos actualizados del autor
     * @return Autor actualizado
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar autor existente", description = "Este endpoint actualiza un autor existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de autor inválidos")
    })
    public ResponseEntity<AuthorModel> update(@PathVariable Long id, @RequestBody AuthorInputModel authorInputModel) {
        return ResponseEntity.ok(authorService.update(id, authorInputModel));
    }

    /**
     * Habilita un autor.
     *
     * @param id ID del autor a habilitar
     * @return Respuesta sin contenido
     */
    @PatchMapping("/{id}/enable")
    @Operation(summary = "Habilitar autor", description = "Este endpoint habilita un autor específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor habilitado con éxito"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado")
    })
    public ResponseEntity<Void> enabled(@PathVariable Long id) {
        authorService.enabled(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deshabilita un autor.
     *
     * @param id ID del autor a deshabilitar
     * @return Respuesta sin contenido
     */
    @PatchMapping("/{id}/disable")
    @Operation(summary = "Deshabilitar autor", description = "Este endpoint deshabilita un autor específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor deshabilitado con éxito"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado")
    })
    public ResponseEntity<Void> disabled(@PathVariable Long id) {
        authorService.disabled(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Elimina un autor.
     *
     * @param id ID del autor a eliminar
     * @return Respuesta sin contenido
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar autor", description = "Este endpoint elimina un autor específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado")
    })
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        authorService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
