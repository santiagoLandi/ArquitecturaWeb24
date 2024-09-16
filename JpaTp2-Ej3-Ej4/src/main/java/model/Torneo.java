package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Torneo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTorneo;
    @Column(nullable = false)
    private String nombreTorneo;
    @OneToMany(mappedBy = "torneo",fetch = FetchType.LAZY)
    private List<Equipo> equiposParticipantes;

    public Torneo() {
        super();
    }
    public Torneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
        this.equiposParticipantes = new ArrayList<Equipo>();
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }

    public List<Equipo> getEquiposParticipantes() {
        return new ArrayList<>(equiposParticipantes);
    }
    public void addEquipo(Equipo equipo) {
        if (!equiposParticipantes.contains(equipo)) {
            equiposParticipantes.add(equipo);
        }
    }

    @Override
    public String toString() {
        return "nombreTorneo='" + nombreTorneo + '\'' +
                ", equiposParticipantes=" + equiposParticipantes +
                '}';
    }
}
