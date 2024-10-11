package org.tudai.entregable3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.entregable3.dto.ReporteCarreraDTO;
import org.tudai.entregable3.repository.InscripcionRepository;

import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public List<ReporteCarreraDTO> getReporteCarreras(){
        return inscripcionRepository.getReporteCarreras();
    }

    public void actualizarInscripcion(Integer anio, boolean esGraduado, Long estudianteId){
        inscripcionRepository.actualizarInscripcion(anio,esGraduado,estudianteId);
    }

}
