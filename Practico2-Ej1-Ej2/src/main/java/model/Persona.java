package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersona;
    @Column(nullable = false)
    private String nombre;
    @Column
    private int edad;
    @ManyToOne
    private Direccion direccion;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "jugadores")
    private List<Turno> turnos=new ArrayList<>();

    public Persona() {
        super();
    }

    public Persona(String nombre, int edad, Direccion direccion) {
        super();
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
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

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void addTurno(Turno turno) {
        if(!turnos.contains(turno)) {
            turnos.add(turno);
        }
    }

    public void setTurnos(ArrayList<Turno> turnos) {
        this.turnos = turnos;
    }

    @Override
    public String toString() {
        return "nombre= " + nombre +
                ", edad= " + edad +
                direccion;
    }

}
