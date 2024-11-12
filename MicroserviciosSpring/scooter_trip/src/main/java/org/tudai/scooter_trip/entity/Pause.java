package org.tudai.scooter_trip.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Pause {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startPause;
    private Date endPause;
    private Boolean exceededTime;
    private Long scooterTripId;

    public Pause() {}

    public Pause(Date startPause, Date endPause, Boolean exceededTime, Long scooterTripId) {
        this.startPause = startPause;
        this.endPause = endPause;
        this.exceededTime = exceededTime;
        this.scooterTripId = scooterTripId;
    }
}
