package org.tudai.scooter_trip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.scooter_trip.dto.PauseDTO;
import org.tudai.scooter_trip.entity.Pause;
import org.tudai.scooter_trip.entity.ScooterTrip;
import org.tudai.scooter_trip.repository.PauseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PauseService {


    private PauseRepository pauseRepository;
    private ScooterTripService scooterTripService;

    @Autowired
    public PauseService(PauseRepository pauseRepository,ScooterTripService scooterTripService) {
        this.pauseRepository = pauseRepository;
        this.scooterTripService = scooterTripService;
    }

    public PauseDTO save(PauseDTO pauseDTO) {
        Pause pause = new Pause(pauseDTO.getStartPause(),pauseDTO.getEndPause(),pauseDTO.getExceededTime(),pauseDTO.getScooterTripId());
        pauseRepository.save(pause);
        Long scooterTripId = pause.getScooterTripId();
        Long pauseId = pause.getId();
        scooterTripService.addPauseToTrip(scooterTripId,pauseId);
        return convertToDTO(pause);
    }

    public void deleteById(Long id){
        pauseRepository.deleteById(id);
    }

    public PauseDTO convertToDTO(Pause pause) {
        return new PauseDTO(pause.getStartPause(),pause.getEndPause(),pause.getExceededTime(),pause.getScooterTripId());
    }

    public List<PauseDTO> findAll(){
        List<Pause> pauses = pauseRepository.findAll();
        List<PauseDTO> pauseDTOs = new ArrayList<PauseDTO>();
        for (Pause pause : pauses) {
            pauseDTOs.add(convertToDTO(pause));
        }
        return pauseDTOs;
    }
}
