package org.example.tp3springboot.Repository;

import org.example.tp3springboot.Model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
    @Query("SELECT t FROM Direccion t where t.calle = :calle")
    public List<Direccion> findAllByCalle(String calle);

    @Query("SELECT t FROM Direccion t where t.numero = :numero")
    public List<Direccion> findAllByNumero(Integer numero);
}
