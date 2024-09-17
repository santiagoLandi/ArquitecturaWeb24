package dao;

import model.Direccion;

import java.util.List;

public interface DireccionDAO {

    void insert(Direccion d);

    Direccion findById(int id);

    List<Direccion> findAll();

    void update(int id, String calle, String ciudad);

    void delete(Direccion d);
}
