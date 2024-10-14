package org.tudai.entregable3.service;

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
            CarreraDTO carreraDTO = (CarreraDTO) convertToDTO(carrera);
            carrerasDTO.add(carreraDTO);
        }
        return carrerasDTO;
    }
    private Object convertToDTO(Carrera carrera) {
        return new CarreraDTO(carrera.getNombre());
    }
    public List<CarreraConCantidadInscriptosDTO> getCarreraCantidadInscriptos(){
        return carreraRepository.carrerasConInscriptos();
    }
}
