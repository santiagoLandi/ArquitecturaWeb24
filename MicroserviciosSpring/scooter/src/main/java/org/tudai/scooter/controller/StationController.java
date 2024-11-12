package org.tudai.scooter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tudai.scooter.dto.StationDTO;
import org.tudai.scooter.entity.Station;
import org.tudai.scooter.service.StationService;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {

    @Autowired
    private StationService service;

    @PostMapping("/register")
    public ResponseEntity<Void>registerStation(@RequestBody StationDTO stationDTO){
        try {
            service.save(stationDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteStation(@PathVariable Long id){
        try{
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationDTO> getStation(@PathVariable Long id){
        try{
            StationDTO stationDTO = service.findById(id);
            if(stationDTO == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(stationDTO);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<StationDTO>> getAllStations(){
        try{
            List<StationDTO>stations = service.getAll();
            if(stations == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(stations);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }



}
