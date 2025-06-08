package br.com.floodalert.repositories;

import br.com.floodalert.models.Incident;
import br.com.floodalert.models.Shelter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface IncidentRepository extends JpaRepository<Incident, UUID> {

    Page<Incident> findAll(Pageable pageable);

    Optional<Incident> findById(UUID id);

    boolean existsById(UUID id);

    void deleteById(UUID id);

}
