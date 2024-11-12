package org.tudai.scooter.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.tudai.scooter.dto.ScooterDTO;
import org.tudai.scooter.dto.ScooterReportDTO;
import org.tudai.scooter.entity.Scooter;
import org.tudai.scooter.feign.MaintenanceClient;
import org.tudai.scooter.repository.ScooterRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScooterService {

    private ScooterRepository scooterRepository;
    private StationService stationService;
    private MaintenanceClient maintenanceClient;

    @Autowired
    public ScooterService(ScooterRepository scooterRepository, StationService stationService, MaintenanceClient maintenanceClient) {
        this.scooterRepository = scooterRepository;
        this.stationService = stationService;
        this.maintenanceClient = maintenanceClient;
    }

    public void save(ScooterDTO scooterDTO) {
        Scooter scooter = new Scooter();
        scooter.setLocation(scooterDTO.getLocation());
        scooter.setKilometersTraveled(scooterDTO.getKilometersTraveled());
        scooter.setHoursUsed(scooterDTO.getHoursUsed());
        scooter.setScooterTripsIds(scooterDTO.getScooterTripsIds());
        scooter.setCurrentStationId(scooterDTO.getCurrentStationId());
        scooterRepository.save(scooter);
    }

    public void deleteById(Long id) {
        scooterRepository.deleteById(id);
    }

    public List<ScooterDTO>findAll(){
        List<Scooter> scooters = scooterRepository.findAll();
        List<ScooterDTO> scooterDTOs = new ArrayList<ScooterDTO>();
        for (Scooter scooter : scooters) {
            scooterDTOs.add(convertToDTO(scooter));
        }
        return scooterDTOs;
    }

    public List<ScooterReportDTO>findAllForReport(){
        List<Scooter> scooters = scooterRepository.findAll();
        List<ScooterReportDTO> scooterDTOs = new ArrayList<ScooterReportDTO>();
        for (Scooter scooter : scooters) {
            scooterDTOs.add(convertToReportDTO(scooter));
        }
        return scooterDTOs;
    }

    public ScooterDTO findById(Long id) {
        return scooterRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Scooter no encontrado con id " + id));
    }


    public void updateScooter(Long id, ScooterDTO scooterDTO) {
        Scooter scooter = scooterRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Scooter no encontrado con id " + id));
        scooter.setStatus(scooterDTO.getStatus());
        scooter.setKilometersTraveled(scooterDTO.getKilometersTraveled());
        scooter.setHoursUsed(scooterDTO.getHoursUsed());
        scooter.setScooterTripsIds(scooterDTO.getScooterTripsIds());
        scooter.setCurrentStationId(scooterDTO.getCurrentStationId());
        scooter.setLocation(scooterDTO.getLocation());
        scooter.setMaintenanceIds(scooterDTO.getMaintenanceRecordIds());
        scooterRepository.save(scooter);
    }

    public ScooterDTO convertToDTO(Scooter scooter) {
        return new ScooterDTO(scooter);
    }

    public ScooterReportDTO convertToReportDTO(Scooter scooter) {
        return new ScooterReportDTO(scooter);
    }


    public void onMaintenance(Long scooterId) {
        Scooter scooter = scooterRepository.findById(scooterId).orElseThrow(()-> new EntityNotFoundException("Scooter no encontrado con id " + scooterId));
        scooter.setStatus(false);
        scooterRepository.save(scooter);
    }

    public void endMaintenance(Long scooterId) {
        Scooter scooter = scooterRepository.findById(scooterId).orElseThrow(()-> new EntityNotFoundException("Scooter no encontrado con id " + scooterId));
        scooter.setStatus(true);
        scooterRepository.save(scooter);
    }







}
