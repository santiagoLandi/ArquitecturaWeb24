package org.tudai.entregable3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tudai.entregable3.dto.ReporteCarreraDTO;
import org.tudai.entregable3.model.Estudiante;
import org.tudai.entregable3.service.InscripcionService;

import java.util.List;

@RestController
@RequestMapping("inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @RequestMapping("reporteCarreras")
    public List<ReporteCarreraDTO>getReporteCarreras(){
        return inscripcionService.getReporteCarreras();
    }
    @PutMapping("/graduarEstudiante/{anio}/{estudiante}")
    public void actualizarInscripcion(@PathVariable Integer anio, @RequestBody Estudiante estudiante,@){
        inscripcionService.actualizarInscripcion(anio,true,estudiante.getId());
    }

}
