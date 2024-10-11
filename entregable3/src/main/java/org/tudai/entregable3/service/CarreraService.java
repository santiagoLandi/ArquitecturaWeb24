package org.tudai.entregable3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.entregable3.dto.CarreraConCantidadInscriptosDTO;
import org.tudai.entregable3.repository.CarreraRepository;

import java.util.List;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;


    public List<CarreraConCantidadInscriptosDTO> getCarreraCantidadInscriptos(){
        return carreraRepository.carrerasConInscriptos();
    }
}
