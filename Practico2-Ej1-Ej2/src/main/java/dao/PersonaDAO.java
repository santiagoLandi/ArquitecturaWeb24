package dao;

import model.Direccion;
import model.Persona;

import java.util.List;

public interface PersonaDAO {

    void insert(Persona p);

    Persona findById(int id);

    List<Persona> findAll();

    void update(int id, String nombre, int edad, Direccion direccion);

    void delete(Persona p);
}
