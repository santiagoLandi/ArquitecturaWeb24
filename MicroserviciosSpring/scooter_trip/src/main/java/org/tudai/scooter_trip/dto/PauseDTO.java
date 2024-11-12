package org.tudai.scooter_trip.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PauseDTO {
    private Date startPause;
    private Date endPause;
    private Boolean exceededTime;
    private Long scooterTripId;

    public PauseDTO() {}

    public PauseDTO(Date startPause, Date endPause, Boolean exceededTime,Long scooterTripId) {
        this.startPause = startPause;
        this.endPause = endPause;
        this.exceededTime = exceededTime;
        this.scooterTripId = scooterTripId;
    }


}
