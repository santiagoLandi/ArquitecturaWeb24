package org.example.tp3springboot.Repository;

import org.example.tp3springboot.Model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    @Query("SELECT t FROM Persona t where t.apellido = :apellido")
    List<Persona> findAllByApellido(String apellido);

    @Query("SELECT t FROM Persona t where t.nombre = :nombre")
    List<Persona> findAllByNombre(String nombre);





}
