package com.ailtonluiz.bookmanager.api.controller;

import com.ailtonluiz.bookmanager.api.assembler.NationalityInputDisassembler;
import com.ailtonluiz.bookmanager.api.assembler.NationalityModelAssembler;
import com.ailtonluiz.bookmanager.api.model.NationalityModel;
import com.ailtonluiz.bookmanager.api.model.input.NationalityInput;
import com.ailtonluiz.bookmanager.domain.model.Nationality;
import com.ailtonluiz.bookmanager.domain.repository.NationalityRepository;
import com.ailtonluiz.bookmanager.domain.service.RegisterNationalityService;
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
 * Controlador REST para gestionar nacionalidades.
 * Este controlador proporciona endpoints para operaciones CRUD de nacionalidades.
 */
@RestController
@RequestMapping("api/v1/nationalities")
@Tag(name = "Nationalities", description = "API para gestión de nacionalidades")
public class NationalityController {

    @Autowired
    private NationalityRepository nationalityRepository;

    @Autowired
    private RegisterNationalityService registerNationalityService;

    @Autowired
    private NationalityModelAssembler nationalityModelAssembler;

    @Autowired
    private NationalityInputDisassembler nationalityInputDisassembler;

    /**
     * Lista todas las nacionalidades.
     * Este endpoint devuelve todas las nacionalidades actualmente registradas en la base de datos.
     *
     * @return Lista de nacionalidades
     */
    @Operation(
        summary = "Listar todas las nacionalidades",
        description = "Este endpoint devuelve todas las nacionalidades actualmente registradas en la base de datos",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "La lista de nacionalidades se ha recuperado correctamente",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Ejemplo de respuesta",
                        value = "[{\"id\":1, \"name\":\"ESPAÑA\", \"enabled\":true, \"createdAt\":\"2024-01-01T12:00:00Z\", \"updatedAt\":\"2024-01-10T15:30:00Z\"}, {\"id\":2, \"name\":\"MÉXICO\", \"enabled\":true, \"createdAt\":\"2024-01-02T10:00:00Z\", \"updatedAt\":\"2024-01-11T14:20:00Z\"}]"
                    )
                )
            )
        }
    )
    @GetMapping
    public List<NationalityModel> list() {
        List<Nationality> nationalities = nationalityRepository.findAll();
        return nationalityModelAssembler.toCollectionModel(nationalities);
    }

    /**
     * Busca una nacionalidad por ID.
     * Este endpoint devuelve una nacionalidad específica por su ID.
     *
     * @param nationalityId ID de la nacionalidad a buscar
     * @return Nacionalidad encontrada
     */
    @Operation(
        summary = "Buscar nacionalidad por ID",
        description = "Este endpoint devuelve una nacionalidad específica por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "La nacionalidad se ha encontrado correctamente",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Ejemplo de respuesta",
                        value = "{\"id\":1, \"name\":\"ESPAÑA\", \"enabled\":true, \"createdAt\":\"2024-01-01T12:00:00Z\", \"updatedAt\":\"2024-01-10T15:30:00Z\"}"
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Nacionalidad no encontrada"
            )
        }
    )
    @GetMapping("/{nationalityId}")
    public NationalityModel findById(@PathVariable Long nationalityId) {
        Nationality nationality = registerNationalityService.findOrFail(nationalityId);
        return nationalityModelAssembler.toModel(nationality);
    }

    /**
     * Añade una nueva nacionalidad.
     * Este endpoint crea una nueva nacionalidad en el sistema.
     *
     * @param nationalityInputModel Datos de la nacionalidad a crear
     * @return Nacionalidad creada
     */
    @Operation(
        summary = "Añadir nueva nacionalidad",
        description = "Este endpoint crea una nueva nacionalidad en el sistema",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Nacionalidad creada correctamente",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Ejemplo de respuesta",
                        value = "{\"id\":1, \"name\":\"ESPAÑA\", \"enabled\":true, \"createdAt\":\"2024-01-01T12:00:00Z\", \"updatedAt\":\"2024-01-10T15:30:00Z\"}"
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
    public NationalityModel add(@RequestBody @Valid NationalityInput nationalityInput) {
        Nationality nationality = nationalityInputDisassembler.toDomainObject(nationalityInput);
        nationality = registerNationalityService.save(nationality);
        return nationalityModelAssembler.toModel(nationality);
    }

    /**
     * Actualiza una nacionalidad existente.
     * Este endpoint actualiza una nacionalidad existente por su ID.
     *
     * @param nationalityId         ID de la nacionalidad a actualizar
     * @param nationalityInputModel Datos actualizados de la nacionalidad
     * @return Nacionalidad actualizada
     */
    @Operation(
        summary = "Actualizar nacionalidad existente",
        description = "Este endpoint actualiza una nacionalidad existente por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Nacionalidad actualizada correctamente",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Ejemplo de respuesta",
                        value = "{\"id\":1, \"name\":\"ESPAÑA\", \"enabled\":true, \"createdAt\":\"2024-01-01T12:00:00Z\", \"updatedAt\":\"2024-01-10T15:30:00Z\"}"
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Nacionalidad no encontrada"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Datos inválidos proporcionados"
            )
        }
    )
    @PutMapping("/{nationalityId}")
    public NationalityModel update(@PathVariable Long nationalityId, @RequestBody @Valid NationalityInput nationalityInput) {
        Nationality currentNationality = registerNationalityService.findOrFail(nationalityId);
        nationalityInputDisassembler.copyToDomainObject(nationalityInput, currentNationality);
        currentNationality = registerNationalityService.save(currentNationality);
        return nationalityModelAssembler.toModel(currentNationality);
    }

    /**
     * Habilita una nacionalidad.
     * Este endpoint habilita una nacionalidad específica por su ID.
     *
     * @param nationalityId ID de la nacionalidad a habilitar
     * @return Respuesta sin contenido
     */
    @Operation(
        summary = "Habilitar nacionalidad",
        description = "Este endpoint habilita una nacionalidad específica por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Nacionalidad habilitada correctamente"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Nacionalidad no encontrada"
            )
        }
    )
    @PutMapping("/{nationalityId}/enable")
    public void enabled(@PathVariable Long nationalityId) {
        registerNationalityService.enabledNationality(nationalityId);
    }

    /**
     * Deshabilita una nacionalidad.
     * Este endpoint deshabilita una nacionalidad específica por su ID.
     *
     * @param nationalityId ID de la nacionalidad a deshabilitar
     * @return Respuesta sin contenido
     */
    @Operation(
        summary = "Deshabilitar nacionalidad",
        description = "Este endpoint deshabilita una nacionalidad específica por su ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Nacionalidad deshabilitada correctamente"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Nacionalidad no encontrada"
            )
        }
    )
    @PutMapping("/{nationalityId}/disabled")
    public void disabled(@PathVariable Long nationalityId) {
        registerNationalityService.disabledNationality(nationalityId);
    }

    @Operation(
        summary = "Eliminar nacionalidad",
        description = "Este endpoint elimina una nacionalidad específica por su ID",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Nacionalidad eliminada correctamente"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Nacionalidad no encontrada"
            ),
            @ApiResponse(
                responseCode = "409",
                description = "Nacionalidad en uso, no puede ser eliminada"
            )
        }
    )
    @DeleteMapping("/{nationalityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long nationalityId) {
        registerNationalityService.delete(nationalityId);
    }
}
