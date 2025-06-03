package br.com.floodalert.controllers;


import br.com.floodalert.models.Alert;
import br.com.floodalert.models.dtos.AlertDTO;
import br.com.floodalert.services.AlertService;
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

import java.util.UUID;

@RestController
@RequestMapping("/alert")
@Tag(name = "Alert", description = "Operações CRUD para alertas de enchente")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @GetMapping
    @Operation(summary = "Listar todos os alertas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Alertas listados com sucesso")
            })
    public Page<AlertDTO> showAllAlertsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "alertDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return alertService.getAllAlerts(pageable)
                .map(AlertDTO::fromEntity);
    }


    @PostMapping
    @Operation(summary = "Cadastrar um novo alerta",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Alerta criado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Não foi possivel cadastrar o novo Alerta")
            })
    private AlertDTO createAlert(@Parameter(description = "JSON de um AlertDTO") @RequestBody AlertDTO alert) {
        Alert toMap = alert.toEntity();
        Alert response = alertService.postAlert(toMap);
        return AlertDTO.fromEntity(response);
    }


    @PutMapping
    @Operation(summary = "Atualizar uma motocicleta existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Alerta atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Alerta não encontrado")
            })
    public AlertDTO updateMotorclycle(@Parameter(description = "JSON de uma motocicleta") @RequestBody AlertDTO dto) {
        return AlertDTO.fromEntity(alertService.updateAlert(dto.toEntity()));
    }



    @DeleteMapping("/{id:[0-9a-fA-F\\-]{36}}")
    @Operation(summary = "Deletar motocicleta por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Alerta deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Alerta não encontrado")
            })
    public void deleteMotocycleById(@PathVariable UUID id) {
        alertService.deleteAlert(id);
    }
}
