package com.intellij;


import jakarta.persistence.*;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPersona;
    @Column(nullable = false)
    private String nombre;
    @Column
    private int edad;
    @ManyToOne
    private Direccion direccion;


    public Persona(int idPersona, String nombre, int edad, Direccion direccion) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
    }
    public Persona(String nombre, int edad, Direccion direccion) {
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
    }

    public Persona() {}

    public int getIdPersona() {
        return idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", direccion=" + direccion +
                '}';
    }
}
