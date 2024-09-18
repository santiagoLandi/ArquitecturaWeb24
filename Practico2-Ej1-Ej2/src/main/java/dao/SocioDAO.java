package dao;

import jakarta.persistence.EntityManager;
import model.Persona;
import model.Socio;
import model.Turno;

import java.util.List;

public interface SocioDAO {
    void insert(Socio s);

    Socio findById(int id);

    List<Socio> findAll();

    void update(int id, String tipo);

    void delete(Socio s);

    List<Persona> getSociosPorTurno(Turno turno);

    boolean isSocio(Persona persona, EntityManager em);
}
