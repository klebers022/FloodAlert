package br.com.floodalert.controllers;


import br.com.floodalert.models.DangerArea;
import br.com.floodalert.models.Shelter;
import br.com.floodalert.models.dtos.DangerAreaDTO;
import br.com.floodalert.models.dtos.ShelterDTO;
import br.com.floodalert.services.ShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shelter")
@Tag(name = "Shelter", description = "Operações CRUD para Abrigos de enchente")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    @GetMapping
    @Operation(summary = "Listar todos as Abrigos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Abrigos listadas com sucesso")
            })
    public Page<ShelterDTO> showAllSheltersPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "location") String sortBy)  {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return shelterService.getAllShelters(pageable)
                .map(ShelterDTO::fromEntity);
    }

    @PostMapping
    @Operation(summary = "Cadastrar umo novo Abrigo",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Abrigo criado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Não foi possivel cadastrar o novo Abrigo")
            })
    private ShelterDTO createShelter(@Parameter(description = "JSON de um ShelterDTO") @RequestBody ShelterDTO shelter) {
        ShelterDTO toMap = ShelterDTO.fromEntity(shelter.toEntity(shelter));
        Shelter response = shelterService.postShelter(toMap);
        return ShelterDTO.fromEntity(response);
    }
}
