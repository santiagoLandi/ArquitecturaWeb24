package org.tudai.scooter.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    @ElementCollection
    private List<Long> scootersId;

    public Station() {}

    public Station(String name, String location,List<Long> scootersId) {
        this.name = name;
        this.location = location;
        this.scootersId = scootersId;
    }



}
