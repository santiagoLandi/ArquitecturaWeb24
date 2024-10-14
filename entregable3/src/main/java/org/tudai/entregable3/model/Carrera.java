package org.tudai.entregable3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    // Relación uno a muchos con la entidad Inscripcion
    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // CascadeType.ALL significa que todas las operaciones de cascada se aplicarán a las entidades relacionadas
    private List<Inscripcion> inscripciones;

    public Carrera() {}

    public Carrera(String nombre) {
        this.nombre = nombre;
        this.inscripciones = new ArrayList<>();
    }

    public void addInscripcion(Inscripcion inscripcion) {
        this.inscripciones.add(inscripcion);
        inscripcion.setCarrera(this);
    }
}
