package dao;

import model.Equipo;
import model.Torneo;

import java.util.List;

public interface TorneoDAO {
    void insertTorneo(Torneo torneo);
    void updateTorneo(int id, String nombre);
    void deleteTorneo(Torneo torneo);
    Torneo getTorneo(int id);
    List<Torneo> getTorneos();
    void insertEquipoEnTorneo(Equipo equipo, Torneo torneo);
}
