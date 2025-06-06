package br.com.floodalert.models;


import br.com.floodalert.models.enums.ShelterStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SHELTER")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private Long totalCapacity;

    @NotNull
    private Long occupiedCapacity;

    @NotNull
    private String location;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ShelterStatus status;
}
