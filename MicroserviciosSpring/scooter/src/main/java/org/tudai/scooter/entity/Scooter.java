package org.tudai.scooter.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Scooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean status;
    private String location;
    private Double kilometersTraveled;
    private Double hoursUsed;
    private Long currentStationId;
    @ElementCollection
    private List<Long> scooterTripsIds;
    @ElementCollection
    private List<Long> maintenanceIds;

    public Scooter() {
        this.status = true;
    }

    public Scooter(Long id, Boolean status, String location, Double kilometersTraveled, Double hoursUsed, Long currentStationId) {
        this.id = id;
        this.status = status;
        this.location = location;
        this.kilometersTraveled = kilometersTraveled;
        this.hoursUsed = hoursUsed;
        this.currentStationId = currentStationId;
        this.scooterTripsIds = new ArrayList<>();
        this.maintenanceIds = new ArrayList<>();
    }
}
