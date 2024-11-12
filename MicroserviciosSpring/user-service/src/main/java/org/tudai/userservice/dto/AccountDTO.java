package org.tudai.userservice.dto;

import lombok.Getter;
import org.tudai.userservice.entity.User;

import java.util.Date;
import java.util.List;

@Getter
public class AccountDTO {

    private Double founds;
    private Date dischargeDate;
    private Boolean active;
    private List<Long> usersIds;
    private List<Long>scooterTripsIds;

    public AccountDTO() {}

    public AccountDTO(Double founds, Date dischargeDate) {
        this.founds = founds;
        this.dischargeDate = dischargeDate;
    }

    public AccountDTO(Double founds, Date dischargeDate,Boolean active, List<Long> usersIds,List<Long> scooterTripsIds ) {
        this.founds = founds;
        this.dischargeDate = dischargeDate;
        this.active = active;
        this.usersIds = usersIds;
        this.scooterTripsIds = scooterTripsIds;
    }

    @Override
    public String toString() {
        return  "founds=" + founds +
                ", dischargeDate=" + dischargeDate;
    }
}
