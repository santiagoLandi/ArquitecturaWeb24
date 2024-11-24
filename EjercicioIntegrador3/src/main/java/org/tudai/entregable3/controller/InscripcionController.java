package org.tudai.entregable3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tudai.entregable3.dto.InscripcionDTO;
import org.tudai.entregable3.dto.ReporteCarreraDTO;
import org.tudai.entregable3.model.Carrera;
import org.tudai.entregable3.model.Estudiante;
import org.tudai.entregable3.model.Inscripcion;
import org.tudai.entregable3.service.InscripcionService;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @Autowired
    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @PostMapping("/registroInscripcion")
    public ResponseEntity<Void> save(@RequestBody Inscripcion inscripcion) {
        try{
            inscripcionService.save(inscripcion);
            ResponseEntity.ok(inscripcion);
        } catch (RuntimeException e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/")
    public ResponseEntity<List<InscripcionDTO>> getAll() {
        try {
            List<InscripcionDTO> resultado = inscripcionService.findAll();
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/graduarEstudiante/{anio}/")
    public void actualizarInscripcion(@PathVariable Integer anio, @RequestBody Estudiante estudiante,@RequestBody Carrera carrera) {
        try {
            inscripcionService.actualizarInscripcion(anio, true, estudiante.getId(), carrera.getId());
        } catch (RuntimeException e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/reporteCarreras")
    public ResponseEntity<List<ReporteCarreraDTO>>getReporteCarrera(){
        try{
            List<ReporteCarreraDTO>resultado=inscripcionService.getReporteCarreras();
            return ResponseEntity.ok(resultado);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }





}
