package br.com.floodalert.models.dtos;

import br.com.floodalert.models.Shelter;
import br.com.floodalert.models.enums.ShelterStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShelterDTO {

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

    public static ShelterDTO fromEntity(Shelter shelter) {
        return new ShelterDTO(
            shelter.getName(),
            shelter.getTotalCapacity(),
            shelter.getOccupiedCapacity(),
            shelter.getLocation(),
            shelter.getStatus()
        );
    }


    public Shelter toEntity(ShelterDTO dto) {
        return new Shelter(
                null,
                this.name,
                this.totalCapacity,
                this.occupiedCapacity,
                this.location,
                this.status
        );
    }
}
