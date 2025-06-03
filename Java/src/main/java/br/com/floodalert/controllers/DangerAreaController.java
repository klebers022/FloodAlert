package br.com.floodalert.controllers;

import br.com.floodalert.models.Alert;
import br.com.floodalert.models.DangerArea;
import br.com.floodalert.models.dtos.AlertDTO;
import br.com.floodalert.models.dtos.DangerAreaDTO;
import br.com.floodalert.services.AlertService;
import br.com.floodalert.services.DangerAreaService;
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
@RequestMapping("/danger-area")
@Tag(name = "Danger Area", description = "Operações CRUD para Areas de Risco de enchente")
public class DangerAreaController {

    @Autowired
    private DangerAreaService dangerAreaService;

    @Autowired
    private AlertService alertService;

    @GetMapping
    @Operation(summary = "Listar todos as Areas de Risco",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Areas listadas com sucesso")
            })
    public Page<DangerAreaDTO> showAllDangerAreasPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "coordinates") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return dangerAreaService.getAllDangerAreas(pageable)
                .map(DangerAreaDTO::fromEntity);
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova Area de Risco",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Area de Risco criada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Não foi possivel cadastrar a nova Area de Risco")
            })
    private DangerAreaDTO createDangerArea(@Parameter(description = "JSON de um DangerAreaDTO") @RequestBody DangerAreaDTO area) {
        DangerAreaDTO toMap = DangerAreaDTO.fromEntity(area.toEntity(alertService.getAlertById(area.getAlertId())));
        DangerArea response = dangerAreaService.postDangerArea(toMap);
        return DangerAreaDTO.fromEntity(response);
    }
}
