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
@RequestMapping("/estudiantes")
@Api(value = "EstudianteController", description = "REST API Estudiante description")
public class EstudianteController {


    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Void> save(@RequestBody Estudiante estudiante) {
        try{
            estudianteService.save(estudiante);
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
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try{
            estudianteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            // Si no se encuentra el estudiante con el ID proporcionado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 Not Found
        } catch (RuntimeException e) {
            // Cualquier otro error no esperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 500 Internal Server Error
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante>update(Long id,@RequestBody Estudiante estudiante) {
        try{
            estudiante.setId(id);
            estudianteService.save(estudiante);
            return ResponseEntity.ok(estudiante);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable Long id) {
        try {
            EstudianteDTO estudianteDTO = estudianteService.findById(id);
            if (estudianteDTO != null) {
                return ResponseEntity.ok(estudianteDTO);  // 200 OK con el EstudianteDTO
            } else {
                return ResponseEntity.notFound().build();  // 404 Not Found
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 500 Internal Server Error
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<EstudianteDTO> findByNombre(@PathVariable String nombre) {
        try {
            EstudianteDTO estudianteDTO = estudianteService.findByNombre(nombre);
            if (estudianteDTO != null) {
                return ResponseEntity.ok(estudianteDTO);  // 200 OK con el EstudianteDTO
            } else {
                return ResponseEntity.notFound().build();  // 404 Not Found
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 500 Internal Server Error
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<EstudianteDTO>> findAll() {
        try{
            List<EstudianteDTO>estudiantes=estudianteService.findAll();
            return ResponseEntity.ok(estudiantes);
        }catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<EstudianteDTO> findByApellido(@PathVariable String apellido){
        try{
            EstudianteDTO estudiante=estudianteService.findByApellido(apellido);
            if (estudiante != null) {
                return ResponseEntity.ok(estudiante);
            }else{
                return ResponseEntity.notFound().build();
            }

        }catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/estudiantesOrdenadosPorNombre")
    public ResponseEntity<List<EstudianteDTO>> estudiantesOrdenadosPorNombre() {
        try{
            List<EstudianteDTO>estudiantes=estudianteService.findEstudiantesOrdByNombre();
            return ResponseEntity.ok(estudiantes);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/libreta/{lu}")
    public ResponseEntity<EstudianteDTO> estudiantesLibretaUniv(@PathVariable long lu) {
        try{
            EstudianteDTO estudiante=estudianteService.findByNumeroLibreta(lu);
            if (estudiante != null) {
                return ResponseEntity.ok(estudiante);
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<EstudianteDTO>> estudiantesGenero(@PathVariable String genero) {
        try{
            List<EstudianteDTO>estudiantes=estudianteService.findByGenero(genero);
            return ResponseEntity.ok(estudiantes);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/estudiantesPorCarreraCiudad/{ciudad}/{carrera}")
    public ResponseEntity<List<EstudianteDTO>>getEstudiantesPorCarreraCiudad(@PathVariable String ciudad, @PathVariable String carrera) {
        try{
            List<EstudianteDTO>estudiantes=estudianteService.findByCiudadCarrera(ciudad,carrera);
            return ResponseEntity.ok(estudiantes);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
