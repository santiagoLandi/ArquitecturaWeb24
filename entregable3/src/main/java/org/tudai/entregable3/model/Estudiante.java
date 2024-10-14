package org.tudai.entregable3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombres;
    @Column
    private String apellido;
    @Column
    private String genero;
    @Column
    private Long dni;
    @Column
    private String ciudadResidencia;
    @Column
    private Integer anioNacimiento;
    @Column
    private Long libretaUniv;
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones;

    public Estudiante() {}

    public Estudiante(String nombres, String apellido, String genero, Long dni,Integer anioNacimiento ,String ciudadResidencia, Long libretaUniv) {
        this.nombres = nombres;
        this.apellido = apellido;
        this.genero = genero;
        this.dni = dni;
        this.anioNacimiento = anioNacimiento;
        this.ciudadResidencia = ciudadResidencia;
        this.libretaUniv = libretaUniv;
        this.inscripciones = new ArrayList<>();
    }

    public void addInscripcion(Inscripcion i){
        this.inscripciones.add(i);
        i.setEstudiante(this);
    }
}
