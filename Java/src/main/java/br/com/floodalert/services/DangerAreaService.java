package br.com.floodalert.services;

import br.com.floodalert.exceptions.ExistingAlertException;
import br.com.floodalert.exceptions.ExistingDangerAreaException;
import br.com.floodalert.models.Alert;
import br.com.floodalert.models.DangerArea;
import br.com.floodalert.models.dtos.DangerAreaDTO;
import br.com.floodalert.repositories.AlertRepository;
import br.com.floodalert.repositories.DangerAreaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DangerAreaService {

    private final DangerAreaRepository dangerAreaRepository;
    private final AlertRepository alertRepository;

    //Precisa de um Alert existente no Banco de Dados para criar uma DangerArea!
    public DangerArea postDangerArea(DangerAreaDTO dto) {

        Alert alert = alertRepository.findById(dto.getAlertId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Alert não encontrado com ID: " + dto.getAlertId()
                ));

        DangerArea dangerArea = dto.toEntity(alert);

        return dangerAreaRepository.save(dangerArea);
    }


    public Page<DangerArea> getAllDangerAreas(Pageable pageable) {
        return dangerAreaRepository.findAll(pageable);
    }

    DangerArea getDangerAreaById(UUID id) {
        return dangerAreaRepository.findById(id).orElseThrow(() -> new ExistingDangerAreaException("Alert with this ID" + id +  " does not exist"));
    }

    public DangerArea updateDangerArea(DangerArea area) throws ExistingDangerAreaException {
        if(dangerAreaRepository.existsById(area.getId())) {
            return dangerAreaRepository.save(area);
        } else throw new ExistingAlertException("Danger Area with this ID" + area.getId() +  " already exists");
    }

    public void deleteAlert(UUID id) {
        dangerAreaRepository .deleteById(id);
    }

}
