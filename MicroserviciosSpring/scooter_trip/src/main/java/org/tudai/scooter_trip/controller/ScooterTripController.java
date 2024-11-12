package org.tudai.scooter_trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tudai.scooter_trip.dto.ScooterTripDTO;
import org.tudai.scooter_trip.repository.ScooterTripRepository;
import org.tudai.scooter_trip.service.ScooterTripService;

import java.util.List;

@RestController
@RequestMapping("/scooterTrips")
public class ScooterTripController {

    @Autowired
    private ScooterTripService scooterTripService;

    @PostMapping("/register")
    public ResponseEntity<Void> saveScooterTrip(@RequestBody ScooterTripDTO scooterTripDTO) {
        try{
            scooterTripService.save(scooterTripDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScooterTrip(@PathVariable Long id) {
        try{
            scooterTripService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScooterTripDTO> getScooterTrip(@PathVariable Long id) {
        try{
            ScooterTripDTO scooterTrip =scooterTripService.findById(id);
            return ResponseEntity.ok(scooterTrip);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/countByScooterAndYear")
    public ResponseEntity<Long> countScooterTripByScooterAndYear(@RequestParam Long scooterId,@RequestParam int year){
        try{
            Long count = scooterTripService.countScooterTripByScooterAndYear(scooterId,year);
            return ResponseEntity.ok(count);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ScooterTripDTO>> getAllScooterTrip() {
        try{
            List<ScooterTripDTO>scooterTrips= scooterTripService.getAllScooterTrips();
            return ResponseEntity.ok(scooterTrips);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<ScooterTripDTO>> getScooterTripsByAccountId(@PathVariable Long accountId) {
        try{
            List<ScooterTripDTO>trips= scooterTripService.findTripsByAccountId(accountId);
            if(trips.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(trips);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/scooterTripByAccountId/{accountId}")
    public ResponseEntity<ScooterTripDTO> getScooterTripByAccountId(@PathVariable Long accountId) {
        try{
            ScooterTripDTO trip= scooterTripService.getTripWithAccount(accountId);
            if(trip == null){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(trip);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
