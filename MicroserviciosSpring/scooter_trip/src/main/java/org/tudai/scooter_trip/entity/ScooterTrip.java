package org.tudai.scooter_trip.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ScooterTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId; // PARA LA RELACION CON Account
    private Date startDateTime;
    private Date endDateTime;
    private Double distanceTraveled;
    private Double duration;
    private Double creditsConsumed;
    private Long scooterId;
    @ElementCollection
    private List<Long> pauseIds;

    public ScooterTrip() {}

    public ScooterTrip(Long accountId, Date startDateTime, Date endDateTime, Double distanceTraveled, Double duration, Double creditsConsumed,Long scooterId) {
        this.accountId = accountId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.distanceTraveled = distanceTraveled;
        this.duration = duration;
        this.creditsConsumed = creditsConsumed;
        this.scooterId = scooterId;
        this.pauseIds = new ArrayList<>();
    }
}
