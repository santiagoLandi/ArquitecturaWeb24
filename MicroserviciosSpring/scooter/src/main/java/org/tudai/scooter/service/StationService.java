package org.tudai.scooter.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.scooter.dto.StationDTO;
import org.tudai.scooter.entity.Station;
import org.tudai.scooter.repository.StationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationService {

    @Autowired
    private StationRepository repository;

    public void save(StationDTO stationDTO) {
        Station station = new Station();
        station.setName(stationDTO.getName());
        station.setLocation(stationDTO.getLocation());
        station.setScootersId(stationDTO.getScootersId());
        repository.save(station);
    }

    @Transactional
    public StationDTO findById(Long id) {
        return repository.findById(id).map(this::convertToDto)
                .orElseThrow(() -> new EntityNotFoundException("Station not found with id " + id));
    }

    @Transactional
    public List<StationDTO> getAll() {
        List<Station> stations = repository.findAll();
        List<StationDTO> stationDTOs = new ArrayList<>();
        for (Station station : stations) {
            stationDTOs.add(convertToDto(station));
        }
        return stationDTOs;
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private StationDTO convertToDto(Station station) {
        return new StationDTO(station);
    }

}
