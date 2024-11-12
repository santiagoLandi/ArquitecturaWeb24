package org.tudai.maintenance.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tudai.maintenance.dto.MaintenanceDTO;
import org.tudai.maintenance.dto.ScooterDTO;
import org.tudai.maintenance.entity.Maintenance;
import org.tudai.maintenance.service.MaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping("/register")
    public ResponseEntity<Void>saveMaintenance(Maintenance maintenance) {
        try {
            maintenanceService.saveMaintenance(maintenance);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id ) {
        try{
            maintenanceService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<MaintenanceDTO>> getAllMaintenances() {
        try{
            List<MaintenanceDTO>result= maintenanceService.findAll();
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceDTO> getMaintenance(@PathVariable Long id) {
        try{
            MaintenanceDTO result = maintenanceService.findById(id);
            if(result == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/scooter/{scooterId}")
    public ResponseEntity<List<Long>> getMaintenanceIdsByScooterId(@PathVariable Long scooterId) {
        try{
            List<Long>maintenanceIds= maintenanceService.getMaintenanceIdsByScooterId(scooterId);
            if(maintenanceIds.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(maintenanceIds);

        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/assign-scouter/{maintenanceId}/{scooterId}")
    public ResponseEntity<Void>assignScooterToMaintenance(@PathVariable Long maintenanceId, @PathVariable Long scooterId) {
        try{
            maintenanceService.assignScooterToMaintenance(maintenanceId,scooterId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Scooter o Mantenimiento no encontrados
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // Error interno
        }
    }

    @PatchMapping("/end-maintenance/{maintenanceId}")
    public ResponseEntity<Void>endMaintenance(@PathVariable Long maintenanceId) {
        try{
            maintenanceService.endMaintenance(maintenanceId);
            return ResponseEntity.ok().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // Error interno
        }
    }














}
