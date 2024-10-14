package org.tudai.entregable3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.entregable3.dto.EstudianteDTO;
import org.tudai.entregable3.dto.ReporteCarreraDTO;
import org.tudai.entregable3.model.Carrera;
import org.tudai.entregable3.model.Estudiante;
import org.tudai.entregable3.model.Inscripcion;
import org.tudai.entregable3.repository.InscripcionRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InscripcionService {


    private final InscripcionRepository inscripcionRepository;
    private final EstudianteService estudianteService;
    private final CarreraService carreraService;

    @Autowired
    public InscripcionService(InscripcionRepository inscripcionRepository, EstudianteService estudianteService, CarreraService carreraService) {
        this.inscripcionRepository = inscripcionRepository;
        this.estudianteService = estudianteService;
        this.carreraService = carreraService;
    }

    public List<ReporteCarreraDTO> getReporteCarreras() {
        try {
            List<Object[]> inscriptos = inscripcionRepository.getInscriptosPorAnioYcarrera();
            List<Object[]> egresados = inscripcionRepository.getEgresadosPorAnioYcarrera();

            Map<String, Map<Integer, ReporteCarreraDTO>> reporteMap = new HashMap<>();

            // Procesar inscriptos
            for (Object[] inscripto : inscriptos) {
                String nombreCarrera = (String) inscripto[0];
                int anio = (Integer) inscripto[1];
                int cantInscriptos = (Integer) inscripto[2];

                reporteMap
                        .computeIfAbsent(nombreCarrera, k -> new HashMap<>())
                        .put(anio, new ReporteCarreraDTO(nombreCarrera, anio, cantInscriptos, 0));
            }

            // Procesar egresados
            for (Object[] egresado : egresados) {
                String nombreCarrera = (String) egresado[0];
                int anio = (Integer) egresado[1];
                int cantEgresados = (Integer) egresado[2];

                reporteMap
                        .computeIfAbsent(nombreCarrera, k -> new HashMap<>())
                        .computeIfAbsent(anio, k -> new ReporteCarreraDTO(nombreCarrera, anio, 0, 0))
                        .setCantEgresados(cantEgresados);
            }

            return reporteMap.values().stream()
                    .flatMap(map -> map.values().stream())
                    .sorted(Comparator.comparing(ReporteCarreraDTO::getNombreCarrera)
                            .thenComparing(ReporteCarreraDTO::getAnio))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al generar reporte de carreras", e);
        }
    }


    public void actualizarInscripcion(Integer anio, boolean esGraduado, Long estudianteId,Long idCarrera){
        inscripcionRepository.actualizarInscripcion(anio,esGraduado,estudianteId,idCarrera);
    }

    public void save(Inscripcion inscripcion) {
        try{
            Estudiante estudiante=inscripcion.getEstudiante();
            estudiante.addInscripcion(inscripcion);
            Carrera carrera= inscripcion.getCarrera();
            carrera.addInscripcion(inscripcion);
            inscripcionRepository.save(inscripcion);
        }catch (RuntimeException e){
            throw new RuntimeException("Error al guardar inscripcion", e);
        }


    }
}
