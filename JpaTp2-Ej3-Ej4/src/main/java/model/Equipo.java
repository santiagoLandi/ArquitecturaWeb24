package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEquipo;
    @Column (nullable = false)
    private String nombreEquipo;
    @Column
    private String entidadRepresentada;
    @ManyToOne
    private Torneo torneo;
    @OneToMany(mappedBy = "equipo",fetch=FetchType.LAZY)
    private List<Jugador> jugadores;

    public Equipo() {
        super();
    }

    public Equipo(String nombreEquipo, String entidadRepresentada, Torneo torneo) {
        this.nombreEquipo = nombreEquipo;
        this.entidadRepresentada = entidadRepresentada;
        this.torneo = torneo;
        this.jugadores = new ArrayList<Jugador>();
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getEntidadRepresentada() {
        return entidadRepresentada;
    }

    public void setEntidadRepresentada(String entidadRepresentada) {
        this.entidadRepresentada = entidadRepresentada;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public List<Jugador> getJugadores() {
        return new ArrayList<>(jugadores);
    }

    public void addJugador(Jugador jugador) {
        if (!jugadores.contains(jugador)) {
            jugadores.add(jugador);
        }
    }

    @Override
    public String toString() {
        return "nombreEquipo='" + nombreEquipo + '\'' +
                ", entidadRepresentada='" + entidadRepresentada + '\'' +
                ", torneo=" + torneo +
                ", jugadores=" + jugadores +
                '}';
    }
}
