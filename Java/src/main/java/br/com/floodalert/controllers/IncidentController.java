package br.com.floodalert.controllers;

import br.com.floodalert.models.Incident;
import br.com.floodalert.models.Shelter;
import br.com.floodalert.models.dtos.IncidentDTO;
import br.com.floodalert.models.dtos.ShelterDTO;
import br.com.floodalert.services.IncidentService;
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
@RequestMapping("/incident")
@Tag(name = "Incident", description = "Operações CRUD para Incidentes de enchente")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @GetMapping
    @Operation(summary = "Listar todos as Incidentes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Incidentes listadas com sucesso")
            })
    public Page<IncidentDTO> showAllIncidentsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "coordinates") String sortBy)  {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return incidentService.getAllIncidents(pageable)
                .map(IncidentDTO::fromEntity);
    }

    @PostMapping
    @Operation(summary = "Cadastrar umo novo Incidente",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Incidente criado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Não foi possivel cadastrar o novo Incidente")
            })
    private IncidentDTO createIncident(@Parameter(description = "JSON de um IncidentDTO") @RequestBody IncidentDTO incidentDTO) {
        IncidentDTO toMap = IncidentDTO.fromEntity(incidentDTO.toEntity(incidentDTO));
        Incident response = incidentService.postIncident(toMap);
        return IncidentDTO.fromEntity(response);
    }
}
