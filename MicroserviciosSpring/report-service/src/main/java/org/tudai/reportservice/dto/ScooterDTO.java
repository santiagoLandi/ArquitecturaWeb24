package org.tudai.reportservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.tudai.scooter.entity.Scooter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ScooterDTO {
    private Long id;
    private Boolean status;
    private String location;
    private Double kilometersTraveled;
    private Double hoursUsed;
    private Long currentStationId;
    private List<Long> scooterTripsIds;
    private List<Long> maintenanceIds = new ArrayList<>();

    public ScooterDTO() {}

    public ScooterDTO(Long id, Boolean status, String location, Double kilometersTraveled, Double hoursUsed, Long currentStationId, List<Long> scooterTripsIds, List<Long> maintenanceIds) {
        this.id = id;
        this.status = status;
        this.location = location;
        this.kilometersTraveled = kilometersTraveled;
        this.hoursUsed = hoursUsed;
        this.currentStationId = currentStationId;
        this.scooterTripsIds = scooterTripsIds;
        this.maintenanceIds = maintenanceIds;
    }

    public ScooterDTO(Scooter scooter) {
        this.setId(scooter.getId());
        this.setStatus(scooter.getStatus());
        this.setLocation(scooter.getLocation());
        this.setKilometersTraveled(scooter.getKilometersTraveled());
        this.setHoursUsed(scooter.getHoursUsed());
        this.setCurrentStationId(scooter.getCurrentStationId());
        this.setScooterTripsIds(scooter.getScooterTripsIds());
        this.setMaintenanceIds(scooter.getMaintenanceIds());
    }

}

