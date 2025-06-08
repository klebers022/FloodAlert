package br.com.floodalert.models.dtos;

import br.com.floodalert.models.Incident;
import br.com.floodalert.models.Shelter;
import br.com.floodalert.models.enums.IncidentStatus;
import br.com.floodalert.models.enums.IncidentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class IncidentDTO {

    @NotNull
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private IncidentType type;

    @NotNull
    private String coordinates;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private IncidentStatus status;

    public static IncidentDTO fromEntity(Incident incident) {
        return new IncidentDTO(
                incident.getDescription(),
                incident.getType(),
                incident.getCoordinates(),
                incident.getDate(),
                incident.getStatus()
        );
    }


    public Incident toEntity(IncidentDTO dto) {
        return new Incident(
                null,
                this.description,
                this.type,
                this.coordinates,
                this.date,
                this.status
        );
    }
}
