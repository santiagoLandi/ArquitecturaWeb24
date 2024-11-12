package org.tudai.scooter_trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tudai.scooter_trip.dto.PauseDTO;
import org.tudai.scooter_trip.service.PauseService;

import java.util.List;

@RestController
@RequestMapping("/pauses")
public class PauseController {

    private PauseService pauseService;

    @Autowired
    public PauseController(PauseService pauseService){
        this.pauseService = pauseService;
    }

    @PostMapping("/register")
    public ResponseEntity<PauseDTO> save(PauseDTO pause){
        try{
            pauseService.save(pause);
            return ResponseEntity.ok(pause);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            pauseService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<PauseDTO>> getAll(){
        try{
            List<PauseDTO>pauses= pauseService.findAll();
            if(pauses.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(pauses);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }




}
