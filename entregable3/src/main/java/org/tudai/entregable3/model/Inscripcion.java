package org.tudai.entregable3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer anioInscripcion;
    @Column
    private Integer anioEgreso; // Usar Integer para permitir valores null
    @Column
    private boolean graduado;
    @ManyToOne
    @JoinColumn(name = "idCarrera", nullable = false)
    private Carrera carrera;
    @ManyToOne
    @JoinColumn(name = "idEstudiante", nullable = false)
    private Estudiante estudiante;

    public Inscripcion() {}

    public Inscripcion(Integer anioInscripcion, Carrera carrera, Estudiante estudiante) {
        this.anioInscripcion = anioInscripcion;
        this.anioEgreso = null;
        this.graduado = false;
        this.carrera = carrera;
        this.estudiante = estudiante;
    }
}
