package org.tudai.entregable3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tudai.entregable3.dto.EstudianteDTO;
import org.tudai.entregable3.dto.InscripcionDTO;
import org.tudai.entregable3.dto.ReporteCarreraDTO;
import org.tudai.entregable3.model.Carrera;
import org.tudai.entregable3.model.Estudiante;
import org.tudai.entregable3.model.Inscripcion;
import org.tudai.entregable3.repository.InscripcionRepository;

import java.util.*;
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
            List<ReporteCarreraDTO> inscriptos = inscripcionRepository.getInscriptosPorAnioYcarrera();
            List<ReporteCarreraDTO> egresados = inscripcionRepository.getEgresadosPorAnioYcarrera();

            Map<String, Map<Integer, ReporteCarreraDTO>> reporteMap = new HashMap<>();

            // Procesar inscriptos
            for (ReporteCarreraDTO inscripto : inscriptos) {
                String nombreCarrera = inscripto.getNombreCarrera();
                int anio = (Integer) inscripto.getAnio();
                int cantInscriptos = inscripto.getCantInscriptos();

                reporteMap
                        .computeIfAbsent(nombreCarrera, k -> new HashMap<>())
                        .put(anio, new ReporteCarreraDTO(nombreCarrera, anio, cantInscriptos, 0));
            }

            // Procesar egresados
            for (ReporteCarreraDTO egresado : egresados) {
                String nombreCarrera = egresado.getNombreCarrera();
                int anio = (Integer) egresado.getAnio();
                int cantEgresados = (Integer) egresado.getCantEgresados();

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
    /*
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
     */
    public void save(Inscripcion inscripcion) {
        try {
            // Verifica que el estudiante y la carrera existan
            Estudiante estudiante = estudianteService.findEntityById(inscripcion.getEstudiante().getId());
            Carrera carrera = carreraService.findEntityById(inscripcion.getCarrera().getId());

            // Asigna correctamente las inscripciones al estudiante y carrera
            estudiante.addInscripcion(inscripcion);
            carrera.addInscripcion(inscripcion);

            // Guarda la inscripción
            inscripcionRepository.save(inscripcion);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al guardar la inscripción", e);
        }
    }

    public List<InscripcionDTO> findAll() {
        List<Inscripcion> inscripciones = inscripcionRepository.findAll();
        return inscripciones.stream().map(inscripcion -> {
            Carrera carrera = inscripcion.getCarrera();
            Estudiante estudiante = inscripcion.getEstudiante();

            return new InscripcionDTO(
                    inscripcion.getAnioInscripcion(),
                    inscripcion.getAnioEgreso(),
                    inscripcion.isGraduado(),
                    carrera != null ? carrera.getNombre() : null, // Nombre de la carrera
                    estudiante != null ? estudiante.getNombres() + " " + estudiante.getApellido() : null // Nombre del estudiante
            );
        }).collect(Collectors.toList());
    }
    /*
    public List<InscripcionDTO> findAll() {
        List<InscripcionDTO> resultado = new ArrayList<>();
        List<Inscripcion> inscripciones = inscripcionRepository.findAll();

        for (Inscripcion inscripcion : inscripciones) {
            Carrera carrera = inscripcion.getCarrera();
            Estudiante estudiante = inscripcion.getEstudiante();

            InscripcionDTO dto = new InscripcionDTO(
                    inscripcion.getAnioInscripcion(),
                    inscripcion.getAnioEgreso(),
                    inscripcion.isGraduado(),
                    carrera != null ? carrera.getNombre() : null, // Nombre de la carrera
                    estudiante != null ? estudiante.getNombres() + " " + estudiante.getApellido() : null // Nombre del estudiante
            );
            resultado.add(dto);
        }
        return resultado;
    }*/
}
