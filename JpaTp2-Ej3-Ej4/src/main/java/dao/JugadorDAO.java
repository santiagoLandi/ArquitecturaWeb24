package dao;

import model.Equipo;
import model.Jugador;

import java.util.List;

public interface JugadorDAO {
    void insertJugador(Jugador jugador);
    void updateJugador(int id, String nombreApellido, String posicion, Equipo equipo);
    void deleteJugador(Jugador jugador);
    Jugador getJugador(int id);
    List<Jugador> getJugadores();
}
