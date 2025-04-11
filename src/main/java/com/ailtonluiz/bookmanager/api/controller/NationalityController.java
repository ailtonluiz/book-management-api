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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/nationalities")
public class NationalityController {

    @Autowired
    private NationalityRepository nationalityRepository;

    @Autowired
    private RegisterNationalityService registerNationalityService;

    @Autowired
    private NationalityModelAssembler nationalityModelAssembler;

    @Autowired
    private NationalityInputDisassembler nationalityInputDisassembler;

    //@Operation(summary = "Listar todas las nacionalidades.", description = "Este endpoint retorna todas las nacionalidades actualmente registradas en la base de datos")
    @Operation(
            summary = "Listar todas las nacionalidades",
            description = "Este endpoint retorna todas las nacionalidades actualmente registradas en la base de datos",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "La lista de nacionalidades se ha recuperado correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "Ejemplo de respuesta",
                                            value = "[{\"id\":1, \"name\":\"Brasil\", \"enabled\":\"true\", \"createdAt\":\"2024-01-01T12:00:00Z\", \"updatedAt\":\"2024-01-10T15:30:00Z\"}, {\"id\":2, \"name\":\"España\", \"enabled\":\"false\", \"createdAt\":\"2024-02-01T09:15:00Z\", \"updatedAt\":\"2024-02-05T11:45:00Z\"}]"

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

    @GetMapping("/{nationalityId}")
    public NationalityModel findById(@PathVariable Long nationalityId) {

        Nationality nationality = registerNationalityService.findOrFail(nationalityId);
        return nationalityModelAssembler.toModel(nationality);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NationalityModel add(@RequestBody @Valid NationalityInput nationalityInput) {
        Nationality nationality = nationalityInputDisassembler.toDomainObject(nationalityInput);

        nationality = registerNationalityService.save(nationality);
        return nationalityModelAssembler.toModel(nationality);
    }

    @PutMapping("/{nationalityId}")
    public NationalityModel update(@PathVariable Long nationalityId, @RequestBody @Valid NationalityInput nationalityInput) {

        Nationality currentNationality = registerNationalityService.findOrFail(nationalityId);
        nationalityInputDisassembler.copyToDomainObject(nationalityInput, currentNationality);
        currentNationality = registerNationalityService.save(currentNationality);


        return nationalityModelAssembler.toModel(currentNationality);
    }

    @PutMapping("/{nationalityId}/enable")
    public void enabled(@PathVariable Long nationalityId) {
        registerNationalityService.enabledNationality(nationalityId);
    }

    @DeleteMapping("/{nationalityId}/enable")
    public void disabled(@PathVariable Long nationalityId) {
        registerNationalityService.disabledNationality(nationalityId);
    }


    @DeleteMapping("/{nationalityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long nationalityId) {
        registerNationalityService.delete(nationalityId);
    }

}
