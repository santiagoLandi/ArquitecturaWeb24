package model;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTurno;
    @Column
    private LocalDateTime fecha;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Persona> jugadores=new ArrayList<>();

    public Turno() {
        super();
    }

    public Turno(LocalDateTime fecha) {
        super();
        this.fecha = fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void addJugadoresATurno(Persona persona) {
        if(!jugadores.contains(persona)) {
            this.jugadores.add(persona);
            persona.addTurno(this);
        }

    }

    public List<Persona> getJugadores() {
        return new ArrayList<>(jugadores);
    }

    @Override
    public String toString() {
        // Forzar la carga de la lista de jugadores si es necesario
        if (!Hibernate.isInitialized(jugadores)) {
            Hibernate.initialize(jugadores);
        }
        return "fecha= " + fecha + ", jugadores= " + jugadores.toString();
    }

    public void setJugadores(ArrayList<Persona> jugadores) {
        this.jugadores = jugadores;
    }

    public int getJugadoresSize(){
        return jugadores.size();
    }

    public boolean isJugadoresInitialized() {
        // Verifica si la colección jugadores está inicializada
        return Hibernate.isInitialized(jugadores);
    }
}
