package dao;

import model.Equipo;
import model.Torneo;

import java.util.List;

public interface EquipoDAO {
    void insertEquipo(Equipo equipo);
    void updateEquipo(int id, String nombreEquipo, String entidadRepre, Torneo torneo);
    void deleteEquipo(Equipo equipo);
    Equipo getEquipo(int id);
    List<Equipo> getEquipos();

}
