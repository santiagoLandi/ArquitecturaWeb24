package org.tudai.entregable3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tudai.entregable3.dto.ReporteCarreraDTO;
import org.tudai.entregable3.model.Inscripcion;

import java.util.List;
@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    // 2h) Generar un reporte de las carreras, que para cada carrera incluya información de los
    // inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y
    // presentar los años de manera cronológica.

    @Transactional
    @Query("SELECT new org.tudai.entregable3.dto.ReporteCarreraDTO (c.nombre, i.anioInscripcion, COUNT(i),0) "  +
            "FROM Inscripcion i JOIN i.carrera c "  +
            "GROUP BY c.nombre, i.anioInscripcion "  +
            "ORDER BY c.nombre ASC, i.anioInscripcion ASC")
    List<ReporteCarreraDTO>getInscriptosPorAnioYcarrera();

    @Transactional
    @Query("SELECT new org.tudai.entregable3.dto.ReporteCarreraDTO (c.nombre , i.anioEgreso , 0,COUNT(i)) " +
            "FROM Inscripcion i JOIN i.carrera c " +
            "WHERE i.anioEgreso IS NOT NULL " +
            "GROUP BY c.nombre, i.anioEgreso " +
            "ORDER BY c.nombre ASC, i.anioEgreso ASC")
    List<ReporteCarreraDTO>getEgresadosPorAnioYcarrera();


    @Modifying
    @Transactional
    @Query("UPDATE Inscripcion i SET i.anioEgreso =:anio, i.graduado=:esGraduado WHERE i.estudiante.id=:estudianteId AND i.carrera.id=:idCarrera")
    void actualizarInscripcion(@Param("anio")Integer anio, @Param("esGraduado")Boolean esGraduado,@Param("estudianteId") Long estudianteId,@Param("idCarrera") Long idCarrera);

    @Transactional
    @Query("SELECT i FROM Inscripcion i WHERE i.estudiante.id = :estudianteId AND i.carrera.id = :carreraId")
    Inscripcion findByEstudianteIdAndCarreraId(@Param("estudianteId") Long estudianteId, @Param("carreraId") Long carreraId);
}

