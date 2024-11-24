package org.tudai.entregable3.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.entregable3.dto.EstudianteDTO;
import org.tudai.entregable3.model.Estudiante;
import org.tudai.entregable3.repository.EstudianteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    private EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public EstudianteDTO findById(long id) {
        return  estudianteRepository.findById(id)
                .map(this::convertToDTO)  // Convertir el Estudiante a EstudianteDTO
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id " + id));
    }

    private EstudianteDTO convertToDTO(Estudiante estudiante) {
        return new EstudianteDTO(estudiante.getNombres(),estudiante.getApellido(),estudiante.getAnioNacimiento(),estudiante.getGenero(),estudiante.getDni(),estudiante.getCiudadResidencia(),estudiante.getLibretaUniv());
    }

    public void save(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
    }

    public void delete(Long id) {
        estudianteRepository.deleteById(id);
    }

    public  List<EstudianteDTO> findByNombre(String nombre) {
        return estudianteRepository.findByNombre(nombre);
    }

    public List<EstudianteDTO> findByApellido(String apellido) {
        return estudianteRepository.findByApellido(apellido);
    }

    public List<EstudianteDTO> findAll() {
        List<EstudianteDTO> resultado = new ArrayList<>();
        List<Estudiante>estudiantes= estudianteRepository.findAll();
        for (Estudiante estudiante : estudiantes) {
            EstudianteDTO est=  convertToDTO(estudiante);
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
        return estudianteRepository.getEstudiantesOrderByNombre();
    }

    public List<EstudianteDTO>findByCiudadCarrera(String ciudad,String nombreCarrera){
        return estudianteRepository.getEstudiantesByCiudadAndCarrera(ciudad,nombreCarrera);
    }

    public Estudiante findEntityById(Long id) {
         return estudianteRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }
}
