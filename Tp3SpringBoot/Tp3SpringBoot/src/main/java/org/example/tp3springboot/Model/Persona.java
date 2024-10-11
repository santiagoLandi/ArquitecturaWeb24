package org.example.tp3springboot.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data//simplifica todos los setters y getters
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @ManyToOne
    private Direccion direccion;
    @ManyToMany(mappedBy = "personas",fetch = FetchType.LAZY)
    private List<Turno> turnos;

    public Persona() {}

    public Persona(String nombre, String apellido, Direccion direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.turnos = new ArrayList<Turno>();
    }


}
