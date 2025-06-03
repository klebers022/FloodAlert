package br.com.floodalert.services;

import br.com.floodalert.exceptions.ExistingAlertException;
import br.com.floodalert.models.Alert;
import br.com.floodalert.models.DangerArea;
import br.com.floodalert.models.dtos.AlertDTO;
import br.com.floodalert.repositories.AlertRepository;
import br.com.floodalert.repositories.DangerAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private DangerAreaRepository dangerAreaRepository;

    public Alert createAlert(AlertDTO dto) {
        Alert alert = new Alert();
        alert.setTitle(dto.getTitle());
        alert.setDescription(dto.getDescription());
        alert.setAlertDate(dto.getAlertDate());
        alert.setStatus(dto.getStatus());
        alert.setAlertType(dto.getAlertType());

        List<DangerArea> areas = dangerAreaRepository.findAllById(dto.getDangerAreasIds());
        alert.setDangerAreas(areas);

        areas.forEach(area -> area.setAlert(alert));

        return alertRepository.save(alert);
    }

    public Page<Alert> getAllAlerts(Pageable pageable) {
        return alertRepository.findAll(pageable);
    }

    public Alert getAlertById(UUID id) {
        return alertRepository.findById(id).orElseThrow(() -> new ExistingAlertException("Alert with this ID" + id +  " does not exist"));
    }

    public Alert postAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    public Alert updateAlert(Alert alert) throws ExistingAlertException {
        if(alertRepository.existsById(alert.getId())) {
            return alertRepository.save(alert);
        } else throw new ExistingAlertException("Alert with this ID" + alert.getId() +  " already exists");
    }

    public void deleteAlert(UUID id) {
        alertRepository.deleteById(id);
    }
}
