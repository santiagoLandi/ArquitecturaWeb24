package org.tudai.entregable3.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.tudai.entregable3.model.Carrera;
import org.tudai.entregable3.model.Estudiante;
import org.tudai.entregable3.model.Inscripcion;
import org.tudai.entregable3.service.CarreraService;
import org.tudai.entregable3.service.EstudianteService;
import org.tudai.entregable3.service.InscripcionService;

@Component
public class LoadDatabase implements CommandLineRunner {
    private final EstudianteService estudianteService;
    private final CarreraService carreraService;
    private final InscripcionService inscripcionService;

    public LoadDatabase(EstudianteService estudianteService, CarreraService carreraService, InscripcionService inscripcionService) {
        this.estudianteService = estudianteService;
        this.carreraService = carreraService;
        this.inscripcionService = inscripcionService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Crear algunas carreras
        Carrera ingenieria = new Carrera("Ingeniería Informática");
        Carrera medicina = new Carrera("Medicina");
        Carrera derecho = new Carrera("Derecho");

        // Guardar carreras
        carreraService.save(ingenieria);
        carreraService.save(medicina);
        carreraService.save(derecho);

        // Crear algunos estudiantes
        Estudiante estudiante1 = new Estudiante("rober", "perez", "masculino", 123425678L, 1995, "tandil", 11223344L);
        Estudiante estudiante2 = new Estudiante("ana", "gomez", "femenino", 87654321L, 1998, "rosario", 44332211L);
        Estudiante estudiante3 = new Estudiante("carlos", "fernandez", "masculino", 55554444L, 1999, "cordoba", 22334455L);

        // Crear algunas inscripciones
        Inscripcion inscripcion1 = new Inscripcion(2020, null, false, ingenieria, estudiante1);
        Inscripcion inscripcion2 = new Inscripcion(2021, null, false, medicina, estudiante2);
        Inscripcion inscripcion3 = new Inscripcion(2020, 2024, true, derecho, estudiante1); // Egresado
        Inscripcion inscripcion4 = new Inscripcion(2021, null, false, derecho, estudiante3);

        // Añadir inscripciones a estudiantes
        estudiante1.addInscripcion(inscripcion1);
        estudiante1.addInscripcion(inscripcion3);
        estudiante2.addInscripcion(inscripcion2);
        estudiante3.addInscripcion(inscripcion4);

        // Guardar estudiantes (esto también guardará las inscripciones debido al CascadeType.ALL)
        estudianteService.save(estudiante1);
        estudianteService.save(estudiante2);
        estudianteService.save(estudiante3);

        System.out.println("Datos iniciales cargados correctamente.");

    }

}
