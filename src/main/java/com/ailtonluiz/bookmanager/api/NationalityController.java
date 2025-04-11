package com.ailtonluiz.bookmanager.api;

import com.ailtonluiz.bookmanager.api.assembler.NationalityInputDisassembler;
import com.ailtonluiz.bookmanager.api.assembler.NationalityModelAssembler;
import com.ailtonluiz.bookmanager.api.model.NationalityModel;
import com.ailtonluiz.bookmanager.api.model.input.NationalityInput;
import com.ailtonluiz.bookmanager.domain.model.Nationality;
import com.ailtonluiz.bookmanager.domain.repository.NationalityRepository;
import com.ailtonluiz.bookmanager.domain.service.RegisterNationalityService;
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
