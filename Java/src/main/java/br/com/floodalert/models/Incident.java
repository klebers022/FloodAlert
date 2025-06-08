package br.com.floodalert.models;


import br.com.floodalert.models.enums.IncidentStatus;
import br.com.floodalert.models.enums.IncidentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "INCIDENT")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
}
