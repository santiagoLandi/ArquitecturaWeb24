package dao;

import model.Persona;
import model.Socio;

import java.util.List;

public interface SocioDAO {
    void insert(Socio s);

    Socio findById(int id);

    List<Socio> findAll();

    void update(int id, String tipo);

    void delete(Socio s);
}
