package br.com.floodalert.models.dtos;

import br.com.floodalert.models.Alert;
import br.com.floodalert.models.DangerArea;
import br.com.floodalert.models.enums.AlertType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertDTO{

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private LocalDateTime alertDate;

    @NotNull
    private String status;

    @NotNull
    private AlertType alertType;

    private List<UUID> dangerAreasIds;


    public Alert toEntity() {
        Alert alert = new Alert();
        alert.setTitle(this.title);
        alert.setDescription(this.description);
        alert.setAlertDate(this.alertDate);
        alert.setStatus(this.status);
        alert.setAlertType(this.alertType);

        // DangerAreas são populados no service com os objetos buscados pelo repository
        return alert;
    }

    public static AlertDTO fromEntity(Alert alert) {
        AlertDTO dto = new AlertDTO();
        dto.setTitle(alert.getTitle());
        dto.setDescription(alert.getDescription());
        dto.setAlertDate(alert.getAlertDate());
        dto.setStatus(alert.getStatus());
        dto.setAlertType(alert.getAlertType());

        // Extrai só os IDs das DangerAreas
        if (alert.getDangerAreas() != null) {
            List<UUID> ids = alert.getDangerAreas().stream()
                    .map(DangerArea::getId)
                    .collect(Collectors.toList());
            dto.setDangerAreasIds(ids);
        }

        return dto;
    }

}

