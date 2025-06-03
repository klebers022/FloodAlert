package br.com.floodalert.repositories;

import br.com.floodalert.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AlertRepository extends JpaRepository<Alert, UUID> {

    List<Alert> findAll();

    Optional<Alert> findById(UUID id);

    boolean existsById(UUID id);

    void deleteById(UUID id);


}
