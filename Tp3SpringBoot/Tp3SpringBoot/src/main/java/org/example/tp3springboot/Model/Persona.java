package org.example.tp3springboot.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data//simplifica todos los setters y getters
public class Persona {
    @Id
    private long dni;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @ManyToOne
    private Direccion direccion;

    public Persona() {}

    public Persona(long dni, String nombre, String apellido, Direccion direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }



}
