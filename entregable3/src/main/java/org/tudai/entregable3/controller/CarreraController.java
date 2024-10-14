package org.tudai.entregable3.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tudai.entregable3.dto.CarreraConCantidadInscriptosDTO;
import org.tudai.entregable3.dto.CarreraDTO;
import org.tudai.entregable3.model.Carrera;
import org.tudai.entregable3.model.Estudiante;
import org.tudai.entregable3.repository.CarreraRepository;
import org.tudai.entregable3.service.CarreraService;

import java.util.List;

@RestController
@RequestMapping("/carreras")
@Api(value = "CarreraController", description = "REST API Carrera description")
public class CarreraController {

    private final CarreraService carreraService;

    @Autowired
    public CarreraController(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Void> save(@RequestBody Carrera carrera) {
        try{
            carreraService.save(carrera);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            // Maneja errores específicos, por ejemplo, si los datos del estudiante son inválidos
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request
        } catch (RuntimeException e) {
            // Cualquier otro error inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 500 Internal Server Error
        }
    }
    @GetMapping()
    public ResponseEntity<List<CarreraDTO>> findAll() {
        try{
            List<CarreraDTO>carreras=carreraService.findAll();
            return ResponseEntity.ok().body(carreras);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/carreraCantidadInscriptos")
    public ResponseEntity<List<CarreraConCantidadInscriptosDTO>> getCarreraCantidadInscriptos() {
        try{
            List<CarreraConCantidadInscriptosDTO>resultado= carreraService.getCarreraCantidadInscriptos();
            return ResponseEntity.ok(resultado);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
