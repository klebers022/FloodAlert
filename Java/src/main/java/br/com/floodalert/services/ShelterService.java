package br.com.floodalert.services;

import br.com.floodalert.exceptions.ExistingShelterException;
import br.com.floodalert.models.Shelter;
import br.com.floodalert.models.dtos.ShelterDTO;
import br.com.floodalert.repositories.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    public Page<Shelter> getAllShelters(Pageable pageable) {
        return shelterRepository.findAll(pageable);
    }

    public Shelter getShelterById(UUID id) {
        return shelterRepository.findById(id).orElseThrow(() -> new ExistingShelterException("Shelter with this ID" + id +  " does not exist"));
    }

    public Shelter postShelter(ShelterDTO dto) {
        Shelter shelter = dto.toEntity(dto);
        return shelterRepository.save(shelter);
    }

    public Shelter updateShelter(Shelter shelter) {
        if(shelterRepository.existsById(shelter.getId())) {
            return shelterRepository.save(shelter);
        } else throw new ExistingShelterException("Shelter with this ID" + shelter.getId() +  " already exists");
    }

    public void deleteShelter(Shelter shelter) {
        shelterRepository.delete(shelter);
    }
}
