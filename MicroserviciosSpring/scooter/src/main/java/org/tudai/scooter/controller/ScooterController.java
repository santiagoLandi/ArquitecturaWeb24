package org.tudai.scooter.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tudai.scooter.dto.ScooterDTO;
import org.tudai.scooter.dto.ScooterReportDTO;
import org.tudai.scooter.entity.Scooter;
import org.tudai.scooter.service.ScooterService;

import java.util.List;

@RestController
@RequestMapping("/scooters")
@Api(value = "ScooterController", description = "REST API Scooter description")
public class ScooterController {

    private final ScooterService scooterService;

    @Autowired
    public ScooterController(ScooterService scooterService) {
        this.scooterService = scooterService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> saveScooter(ScooterDTO scooterDTO) {
        try {
            scooterService.save(scooterDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            // Maneja errores específicos, por ejemplo, si los datos del estudiante son inválidos
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request
        } catch (RuntimeException e) {
            // Cualquier otro error inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 500 Internal Server Error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScooter(@PathVariable Long id ) {
        try{
            scooterService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ScooterDTO>> getAllScooters() {
        try{
            List<ScooterDTO>resultado= scooterService.findAll();
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/report")
    public ResponseEntity<List<ScooterReportDTO>> getAllScootersForReport() {
        try{
            List<ScooterReportDTO>resultado= scooterService.findAllForReport();
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ScooterDTO> getScooterById(@PathVariable Long id) {
        try{
            ScooterDTO resultado= scooterService.findById(id);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScooterDTO>updateScooter(@PathVariable Long id, @RequestBody ScooterDTO scooter) {
        try{
            scooterService.updateScooter(id,scooter);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(scooter);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/on-maintenance/{scooterId}")
    public ResponseEntity<Void>onMaintenance(@PathVariable Long scooterId) {
        try{
            scooterService.onMaintenance(scooterId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/end-maintenance/{scooterId}")
    public ResponseEntity<Void>endMaintenance(@PathVariable Long scooterId) {
        try{
            scooterService.endMaintenance(scooterId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
