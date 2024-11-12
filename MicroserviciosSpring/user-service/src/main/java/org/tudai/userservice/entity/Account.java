package org.tudai.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double founds;
    @Column(nullable = false)
    private Date dischargeDate;
    private Boolean active;
    @ElementCollection
    private List<Long> usersIds;
    @ElementCollection
    private List<Long>scooterTripsIds;


    public Account() {}

    public Account(Double founds, Date dischargeDate) {
        this.founds = founds;
        this.dischargeDate = dischargeDate;
        this.active = true;
        this.usersIds = new ArrayList<>();
        this.scooterTripsIds = new ArrayList<>();
    }




}
