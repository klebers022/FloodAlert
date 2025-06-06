package br.com.floodalert.repositories;


import br.com.floodalert.models.DangerArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DangerAreaRepository extends JpaRepository<DangerArea, UUID> {

    Page<DangerArea> findAll(Pageable pageable);

    Optional<DangerArea> findById(UUID id);

    boolean existsById(UUID id);

    void deleteById(UUID id);





}
