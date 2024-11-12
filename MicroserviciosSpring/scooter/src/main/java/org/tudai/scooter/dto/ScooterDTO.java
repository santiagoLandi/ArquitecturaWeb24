package org.tudai.scooter.dto;

import lombok.Getter;
import lombok.Setter;
import org.tudai.scooter.entity.Scooter;

import java.util.List;

@Getter
@Setter
public class ScooterDTO {
    private Boolean status;
    private String location;
    private Double kilometersTraveled;
    private Double hoursUsed;
    private Long currentStationId;
    private List<Long> scooterTripsIds;
    private List<Long> maintenanceRecordIds;

    public ScooterDTO(Scooter newScooter) {
        status = newScooter.getStatus();
        location = newScooter.getLocation();
        kilometersTraveled = newScooter.getKilometersTraveled();
        hoursUsed = newScooter.getHoursUsed();
        currentStationId = newScooter.getCurrentStationId();
        this.scooterTripsIds = newScooter.getScooterTripsIds();
        this.maintenanceRecordIds = newScooter.getMaintenanceIds();
    }

    public ScooterDTO() { }


}

