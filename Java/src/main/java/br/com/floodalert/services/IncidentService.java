package br.com.floodalert.services;

import br.com.floodalert.exceptions.ExistingIncidentException;
import br.com.floodalert.exceptions.ExistingShelterException;
import br.com.floodalert.models.Incident;
import br.com.floodalert.models.Shelter;
import br.com.floodalert.models.dtos.IncidentDTO;
import br.com.floodalert.repositories.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    public Page<Incident> getAllIncidents(Pageable pageable) {
        return incidentRepository.findAll(pageable);
    }

    public Incident getIncidentById(UUID id) {
        return incidentRepository.findById(id).orElse(null);
    }

    public Incident postIncident(IncidentDTO dto) {
        Incident incident = dto.toEntity(dto);
        return incidentRepository.save(incident);
    }

    public Incident updateIncident(Incident incident) {
        if(incidentRepository.existsById(incident.getId())) {
            return incidentRepository.save(incident);
        } else throw new ExistingIncidentException("Incident with this ID" + incident.getId() +  " already exists");
    }

    public void deleteIncident(Incident incident) {
        incidentRepository.delete(incident);
    }



}
