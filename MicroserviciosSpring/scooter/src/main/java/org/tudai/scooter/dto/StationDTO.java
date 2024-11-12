package org.tudai.scooter.dto;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.tudai.scooter.entity.Station;

import java.util.List;


@Getter
public class StationDTO {
    private String name;
    private String location;
    private List<Long> scootersId;

    public StationDTO(String name, String location, List<Long> scootersId) {
        this.name = name;
        this.location = location;
        this.scootersId = scootersId;
    }

    public StationDTO(Station station) {
        this.name = station.getName();
        this.location = station.getLocation();
        this.scootersId = station.getScootersId();
    }
}
