package org.example.tp3springboot.Repository;

import org.example.tp3springboot.Model.Persona;
import org.example.tp3springboot.Model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {
    @Query("SELECT t FROM Turno t WHERE t.fechaTurno = :fecha")
    public List<Turno> turnosAunaFecha(Date fecha);

    public List<Persona>getPersonasAsignadasAunTurno(Turno turno);


}
