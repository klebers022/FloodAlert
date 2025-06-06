package br.com.floodalert.repositories;

import br.com.floodalert.models.Shelter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShelterRepository extends JpaRepository<Shelter, UUID> {

    Page<Shelter> findAll(Pageable pageable);

    Optional<Shelter> findById(UUID id);

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
