package org.tudai.maintenance.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.maintenance.dto.MaintenanceDTO;
import org.tudai.maintenance.dto.ScooterDTO;
import org.tudai.maintenance.entity.Maintenance;
import org.tudai.maintenance.feign.ScooterClient;
import org.tudai.maintenance.repository.MaintenanceRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;
    private ScooterClient scooterClient;

    public void saveMaintenance(Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
    }

    public void deleteById(Long id) {
        maintenanceRepository.deleteById(id);
    }

    public List<MaintenanceDTO> findAll() {
        List<Maintenance> maintenances = maintenanceRepository.findAll();
        List<MaintenanceDTO> maintenanceDTOs = new ArrayList<MaintenanceDTO>();
        for (Maintenance maintenance : maintenances) {
            MaintenanceDTO maintenanceDTO = convertToDTO(maintenance);
            maintenanceDTOs.add(maintenanceDTO);
        }
        return maintenanceDTOs;
    }

    public MaintenanceDTO convertToDTO(Maintenance maintenance) {
        return new MaintenanceDTO(maintenance.getMaintenanceDate(),
                maintenance.getMaintenanceDescription(),maintenance.getMaintenanceStatus(),
                maintenance.getScooterId());
    }

    public MaintenanceDTO findById(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance not found with id " + id));
        return convertToDTO(maintenance);
    }

    public List<Long>getMaintenanceIdsByScooterId(Long scooterId) {
        return maintenanceRepository.getMaintenanceIdsByScooterId(scooterId);
    }

    public void assignScooterToMaintenance(Long maintenanceId, Long scooterId) {
        ScooterDTO scooterDTO = scooterClient.findById(scooterId);
        if(scooterDTO == null) {
            throw new EntityNotFoundException("Scooter not found with id " + scooterId);
        }
        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElseThrow(()->new EntityNotFoundException("Maintenance not found with id " + maintenanceId));
        maintenance.setScooterId(scooterId);
        maintenance.setMaintenanceDate(new Date());
        maintenance.setMaintenanceStatus("en mantenimiento");
        scooterClient.onMaintenance(scooterId);
        maintenanceRepository.save(maintenance);
    }

    public void endMaintenance(Long maintenanceId) {
        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElseThrow(()->new EntityNotFoundException("Maintenance not found with id " + maintenanceId));
        maintenance.setMaintenanceStatus("completado");
        Long scooterId = maintenance.getScooterId();
        scooterClient.endMaintenance(scooterId);
        maintenanceRepository.save(maintenance);

    }



}
