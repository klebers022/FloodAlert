package br.com.floodalert.models.dtos;

import br.com.floodalert.models.Alert;
import br.com.floodalert.models.DangerArea;
import br.com.floodalert.models.enums.ThreatLevel;
import br.com.floodalert.repositories.AlertRepository;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DangerAreaDTO {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ThreatLevel threatLevel;

    @NotNull
    private String coordinates;

    @NotNull
    private UUID alertId;


    public static DangerAreaDTO fromEntity(DangerArea area) {
        return new DangerAreaDTO(
                area.getName(),
                area.getDescription(),
                area.getThreatLevel(),
                area.getCoordinates(),
                area.getAlert().getId()
        );
    }


    public DangerArea toEntity(Alert alert) {
        return new DangerArea(
                null,
                this.name,
                this.description,
                this.threatLevel,
                this.coordinates,
                alert
        );
    }

    public static List<DangerAreaDTO> fromEntityList(List<DangerArea> dangerAreas) {
        return dangerAreas.stream()
                .map(DangerAreaDTO::fromEntity)
                .toList();
    }
}

