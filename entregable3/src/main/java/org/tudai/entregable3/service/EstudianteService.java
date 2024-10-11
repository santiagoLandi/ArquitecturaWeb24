package org.tudai.entregable3.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.entregable3.dto.EstudianteDTO;
import org.tudai.entregable3.model.Estudiante;
import org.tudai.entregable3.repository.EstudianteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    public EstudianteDTO findById(long id) {
        return (EstudianteDTO) estudianteRepository.findById(id)
                .map(this::convertToDTO)  // Convertir el Estudiante a EstudianteDTO
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id " + id));
    }

    private Object convertToDTO(Estudiante estudiante) {
        return new EstudianteDTO(estudiante.getNombres(),estudiante.getApellido(),estudiante.getAnioNacimiento(),estudiante.getGenero(),estudiante.getDni(),estudiante.getCiudadResidencia(),estudiante.getLibretaUniv());
    }

    public void save(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
    }

    public  EstudianteDTO findByNombre(String nombre) {
        return estudianteRepository.findByNombre(nombre);
    }

    public EstudianteDTO findByApellido(String apellido) {
        return estudianteRepository.findByApellido(apellido);
    }

    public List<EstudianteDTO> findAll() {
        List<EstudianteDTO> resultado = new ArrayList<>();
        List<Estudiante>estudiantes= estudianteRepository.findAll();
        for (Estudiante estudiante : estudiantes) {
            EstudianteDTO est= (EstudianteDTO) convertToDTO(estudiante);
            resultado.add(est);
        }
        return resultado;
    }

    public List<EstudianteDTO> findByGenero(String genero) {
        return estudianteRepository.getEstudiantesByGenero(genero);
    }

    public EstudianteDTO findByNumeroLibreta(long lu){
        return estudianteRepository.getEstudianteByNumeroLibreta(lu);
    }

    public List<EstudianteDTO>findEstudiantesOrdByNombre(){
        return (List<EstudianteDTO>) estudianteRepository.getEstudiantesOrderByNombre();
    }

    public List<EstudianteDTO>findByCiudadCarrera(String ciudad,String carrera){
        return estudianteRepository.getEstudiantesByCiudadAndCarrera(ciudad,carrera);
    }
}
