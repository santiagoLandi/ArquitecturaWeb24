package org.tudai.entregable3.controller;

import io.swagger.annotations.Api;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tudai.entregable3.dto.EstudianteDTO;
import org.tudai.entregable3.model.Carrera;
import org.tudai.entregable3.model.Estudiante;
import org.tudai.entregable3.repository.EstudianteRepository;
import org.tudai.entregable3.service.EstudianteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("estudiantes")
@Api(value = "EstudianteController", description = "REST API Estudiante description")
public class EstudianteController {

    @Autowired //es mucho mas eficiente q un constructor que lo inicialize sobretodo a escalas grandes. Se instanciaria x cada llamado sino con constructor
    private EstudianteService estudianteService;

    @PostMapping("/registroEstudiante")
    public ResponseEntity<Void> save(@RequestBody Estudiante estudiante) {
        estudianteService.save(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping ("/estudiante/{id}")
    public EstudianteDTO getEstudianteById(@PathVariable Long id) {
        return  estudianteService.findById(id);

    }
    @GetMapping("/nombre/{nombre}")
    public EstudianteDTO findByNombre(@PathVariable String nombre) {
        return estudianteService.findByNombre(nombre);
    }

    @GetMapping("/apellido/{apellido}")
    public EstudianteDTO findByApellido(@PathVariable String apellido){
        return estudianteService.findByApellido(apellido);
    }

    @GetMapping("/estudiantesOrdenadosPorNombre")
    public List<EstudianteDTO> estudiantesOrdenadosPorNombre() {
        return (List<EstudianteDTO>) estudianteService.findEstudiantesOrdByNombre();
    }

    @GetMapping("/libretaUniversitaria/{lu}")
    public EstudianteDTO estudiantesLibretaUniv(@PathVariable long lu) {
        return  estudianteService.findByNumeroLibreta(lu);
    }

    @GetMapping("/genero/{genero}")
    public List<EstudianteDTO> estudiantesGenero(@PathVariable String genero) {
        return estudianteService.findByGenero(genero);
    }

    @GetMapping("/estudiantesPorCarreraCiudad/{ciudad}/{carrera}")
    public List<EstudianteDTO>getEstudiantesPorCarreraCiudad(@PathVariable String ciudad, @PathVariable Carrera carrera) {
        return estudianteService.findByCiudadCarrera(ciudad,carrera.getNombre());
    }

}
