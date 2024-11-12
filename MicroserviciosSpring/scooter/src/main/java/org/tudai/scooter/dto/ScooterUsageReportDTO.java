package org.tudai.scooter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScooterUsageReportDTO {
    private Long scooterId;
    private Double totalDistance;
    private Double totalDuration;

    public ScooterUsageReportDTO(Long scooterId, Double totalDistance, Double totalDuration) {
        this.scooterId = scooterId;
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;
    }

    public ScooterUsageReportDTO() {}
}
