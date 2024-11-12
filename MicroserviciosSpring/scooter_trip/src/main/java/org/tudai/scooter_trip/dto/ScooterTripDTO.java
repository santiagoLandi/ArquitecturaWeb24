package org.tudai.scooter_trip.dto;

import lombok.Getter;
import lombok.Setter;
import org.tudai.userservice.dto.AccountDTO;

import java.util.Date;

@Getter
@Setter
public class ScooterTripDTO {
    private Long id;
    private AccountDTO account;
    private Date startDateTime;
    private Date endDateTime;
    private Double distanceTraveled;
    private Double duration;
    private Double creditsConsumed;
    private Long scooterId;

    public ScooterTripDTO() {}

    public ScooterTripDTO(Long id, AccountDTO account, Date startDateTime, Date endDateTime, Double distanceTraveled, Double duration, Double creditsConsumed,Long scooterId) {
        this.id = id;
        this.account = account;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.distanceTraveled = distanceTraveled;
        this.duration = duration;
        this.creditsConsumed = creditsConsumed;
        this.scooterId = scooterId;
    }

    public ScooterTripDTO(Date startDateTime, Date endDateTime, Double distanceTraveled, Double duration, Double creditsConsumed) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.distanceTraveled = distanceTraveled;
        this.duration = duration;
        this.creditsConsumed = creditsConsumed;
        this.account = new AccountDTO();
    }
    public ScooterTripDTO(Date startDateTime, Date endDateTime, Double distanceTraveled, Double duration, Double creditsConsumed, AccountDTO account) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.distanceTraveled = distanceTraveled;
        this.duration = duration;
        this.creditsConsumed = creditsConsumed;
        this.account = account;
    }
}
