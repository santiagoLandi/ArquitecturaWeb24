package org.tudai.entregable3.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.entregable3.dto.CarreraConCantidadInscriptosDTO;
import org.tudai.entregable3.dto.CarreraDTO;
import org.tudai.entregable3.dto.EstudianteDTO;
import org.tudai.entregable3.model.Carrera;
import org.tudai.entregable3.model.Estudiante;
import org.tudai.entregable3.repository.CarreraRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarreraService {

    private CarreraRepository carreraRepository;

    @Autowired
    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    public void save(Carrera carrera) {
        carreraRepository.save(carrera);
    }
    public List<CarreraDTO> findAll() {
        List<Carrera> carreras = carreraRepository.findAll();
        List<CarreraDTO> carrerasDTO = new ArrayList<>();
        for (Carrera carrera : carreras) {
            CarreraDTO carreraDTO = convertToDTO(carrera);
            carrerasDTO.add(carreraDTO);
        }
        return carrerasDTO;
    }
    private CarreraDTO convertToDTO(Carrera carrera) {
        return new CarreraDTO(carrera.getNombre());
    }

    public List<CarreraConCantidadInscriptosDTO> getCarreraCantidadInscriptos(){
        return carreraRepository.carrerasConInscriptos();
    }

    public CarreraDTO findCarreraById(Long id) {
        return carreraRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Carrera no encontrada con id " + id));

    }

    public Carrera findEntityById(Long id) {
        return carreraRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Carrera no encontrada"));
    }
}
