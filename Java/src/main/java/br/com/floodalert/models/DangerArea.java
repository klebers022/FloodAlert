package br.com.floodalert.models;


import br.com.floodalert.models.enums.ThreatLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor
@Table(name = "DANGER_AREA")
@NoArgsConstructor
public class DangerArea {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
    @ManyToOne
    @JoinColumn(name = "alert_id")
    private Alert alert;



    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DangerArea that = (DangerArea) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
