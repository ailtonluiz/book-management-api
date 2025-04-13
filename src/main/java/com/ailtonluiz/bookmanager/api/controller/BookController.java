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

/**
 * Controlador REST para gestionar libros.
 * Este controlador proporciona endpoints para operaciones CRUD de libros.
 */
@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Libros", description = "API para gestión de libros")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RegisterBookService registerBookService;

    @Autowired
    private BookModelAssembler bookModelAssembler;

    @Autowired
    private BookInputDisassembler bookInputDisassembler;

    /**
     * Lista todos los libros.
     * Este endpoint devuelve todos los libros actualmente registrados en la base de datos.
     *
     * @return Lista de libros
     */
    @Operation(
        summary = "Listar todos los libros",
        description = "Este endpoint devuelve todos los libros actualmente registrados en la base de datos",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "La lista de libros se ha recuperado correctamente",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Ejemplo de respuesta",
                        value = "[{\"id\":1, \"title\":\"Dom Casmurro\", \"author\":{\"id\":1, \"name\":\"Machado de Assis\"}, \"genre\":{\"id\":1, \"name\":\"Romance\"}, \"year\":1899, \"enabled\":true, \"createdAt\":\"2024-01-01T12:00:00Z\", \"updatedAt\":\"2024-01-10T15:30:00Z\"}]"
                    )
                )
            )
        }
    )
    @GetMapping
    public List<BookModel> list() {
        List<Book> books = bookRepository.findAll();
        return bookModelAssembler.toCollectionModel(books);
    }

    /**
     * Busca un libro por ID.
     * Este endpoint devuelve un libro específico por su ID.
     *
     * @param bookId ID del libro a buscar
     * @return Libro encontrado
     */
    @Operation(
        summary = "Buscar libro por ID",
        description = "Este endpoint devuelve un libro específico por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "El libro se ha encontrado correctamente",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Ejemplo de respuesta",
                        value = "{\"id\":1, \"title\":\"Dom Casmurro\", \"author\":{\"id\":1, \"name\":\"Machado de Assis\"}, \"genre\":{\"id\":1, \"name\":\"Romance\"}, \"year\":1899, \"enabled\":true, \"createdAt\":\"2024-01-01T12:00:00Z\", \"updatedAt\":\"2024-01-10T15:30:00Z\"}"
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Libro no encontrado"
            )
        }
    )
    @GetMapping("/{bookId}")
    public BookModel findById(@PathVariable Long bookId) {
        Book book = registerBookService.findOrFail(bookId);
        return bookModelAssembler.toModel(book);
    }

    /**
     * Añade un nuevo libro.
     * Este endpoint crea un nuevo libro en el sistema.
     *
     * @param bookInput Datos del libro a crear
     * @return Libro creado
     */
    @Operation(
        summary = "Añadir nuevo libro",
        description = "Este endpoint crea un nuevo libro en el sistema",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Libro creado correctamente",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Ejemplo de respuesta",
                        value = "{\"id\":1, \"title\":\"Dom Casmurro\", \"author\":{\"id\":1, \"name\":\"Machado de Assis\"}, \"genre\":{\"id\":1, \"name\":\"Romance\"}, \"year\":1899, \"enabled\":true, \"createdAt\":\"2024-01-01T12:00:00Z\", \"updatedAt\":\"2024-01-10T15:30:00Z\"}"
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
    public BookModel add(@RequestBody @Valid BookInput bookInput) {
        try {
            Book book = bookInputDisassembler.toDomainObject(bookInput);
            book = registerBookService.save(book);
            return bookModelAssembler.toModel(book);
        } catch (AuthorNotFoundException | GenreNotFoundException e) {
            throw new TransactionException(e.getMessage(), e);
        }
    }

    /**
     * Actualiza un libro existente.
     * Este endpoint actualiza un libro existente por su ID.
     *
     * @param bookId         ID del libro a actualizar
     * @param bookInput Datos actualizados del libro
     * @return Libro actualizado
     */
    @Operation(
        summary = "Actualizar libro existente",
        description = "Este endpoint actualiza un libro existente por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Libro actualizado correctamente",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Ejemplo de respuesta",
                        value = "{\"id\":1, \"title\":\"Dom Casmurro\", \"author\":{\"id\":1, \"name\":\"Machado de Assis\"}, \"genre\":{\"id\":1, \"name\":\"Romance\"}, \"year\":1899, \"enabled\":true, \"createdAt\":\"2024-01-01T12:00:00Z\", \"updatedAt\":\"2024-01-10T15:30:00Z\"}"
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Libro no encontrado"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Datos inválidos proporcionados"
            )
        }
    )
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

    /**
     * Habilita un libro.
     * Este endpoint habilita un libro específico por su ID.
     *
     * @param bookId ID del libro a habilitar
     * @return Respuesta sin contenido
     */
    @Operation(
        summary = "Habilitar libro",
        description = "Este endpoint habilita un libro específico por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Libro habilitado correctamente"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Libro no encontrado"
            )
        }
    )
    @PutMapping("/{bookId}/enable")
    public void enabled(@PathVariable Long bookId) {
        registerBookService.enabled(bookId);
    }

    /**
     * Deshabilita un libro.
     * Este endpoint deshabilita un libro específico por su ID.
     *
     * @param bookId ID del libro a deshabilitar
     * @return Respuesta sin contenido
     */
    @Operation(
        summary = "Deshabilitar libro",
        description = "Este endpoint deshabilita un libro específico por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Libro deshabilitado correctamente"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Libro no encontrado"
            )
        }
    )
    @PutMapping("/{bookId}/disable")
    public void disabled(@PathVariable Long bookId) {
        registerBookService.disabled(bookId);
    }

    /**
     * Elimina un libro.
     * Este endpoint elimina un libro específico por su ID.
     *
     * @param bookId ID del libro a eliminar
     * @return Respuesta sin contenido
     */
    @Operation(
        summary = "Eliminar libro",
        description = "Este endpoint elimina un libro específico por su ID",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Libro eliminado correctamente"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Libro no encontrado"
            ),
            @ApiResponse(
                responseCode = "409",
                description = "Libro en uso, no puede ser eliminado"
            )
        }
    )
    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long bookId) {
        registerBookService.delete(bookId);
    }
}
