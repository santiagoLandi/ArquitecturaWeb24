package dao;

import model.Persona;
import model.Turno;

import java.time.LocalDateTime;
import java.util.List;

public interface TurnoDAO {
    void insert(Turno t);

    Turno findById(int id);

    List<Turno> findAll();


    void agregarJugador(Turno t, Persona p);

    List<Persona> findAllById(int id);

    void update(int id, LocalDateTime fecha);

    void delete(Turno t);

}
