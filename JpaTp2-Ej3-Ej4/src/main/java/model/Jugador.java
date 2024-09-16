package model;

import jakarta.persistence.*;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJugador;
    @Column(nullable = false)
    private String nombreApellido;
    @Column(nullable = false)
    private String posicion;
    @ManyToOne
    private Equipo equipo;

    public Jugador() {
        super();
    }

    public Jugador(String nombreApellido, String posicion, Equipo equipo) {
        this.nombreApellido = nombreApellido;
        this.posicion = posicion;
        this.equipo = equipo;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "nombreApellido='" + nombreApellido + '\'' +
                ", posicion='" + posicion + '\'' +
                ", equipo=" + equipo +
                '}';
    }
}
