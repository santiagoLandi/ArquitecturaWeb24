package org.example.tp3springboot.Repository;

import org.example.tp3springboot.Model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SocioRepository extends JpaRepository<Socio, Integer> {
    @Query("SELECT t FROM SOCIO WHERE t.idSocio= :id)
    List<Socio>

}
