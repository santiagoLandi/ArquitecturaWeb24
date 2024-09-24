package org.example.tp3springboot.Repository;

import org.example.tp3springboot.Model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    @Query("SELECT t FROM Persona t where t.apellido = :apellido")
    public List<Persona> findAllBySurname(String apellido);

    @Query("SELECT t FROM Persona t where t.nombre = :nombre")
    public List<Persona> findAllByName(String nombre);

}
