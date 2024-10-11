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
    @Query("SELECT (c.nombre, i.anioInscripcion, i.anioEgreso, (SELECT COUNT(i1) FROM Inscripcion i1 WHERE i1.anioEgreso = 0 AND i1.carrera = c), (SELECT COUNT(i2) FROM Inscripcion i2 WHERE i2.anioEgreso != 0 AND i2.carrera = c), e.libretaUniv) FROM Inscripcion i JOIN i.carrera c JOIN i.estudiante e ORDER BY c.nombre ASC, i.anioInscripcion ASC, i.anioEgreso ASC")
    List<ReporteCarreraDTO> getReporteCarreras();

    @Modifying
    @Transactional
    @Query("UPDATE Inscripcion i SET i.anioEgreso =:anio, i.graduado=:esGraduado WHERE i.estudiante.id=:estudianteId ")
    void actualizarInscripcion(@Param("anio")Integer anio, @Param("esGraduado")Boolean esGraduado,@Param("estudianteId") Long estudianteId);

    @Transactional
    @Query("SELECT i FROM Inscripcion i WHERE i.estudiante.id = :estudianteId AND i.carrera.idCarrera = :carreraId")
    Inscripcion findByEstudianteIdAndCarreraId(@Param("estudianteId") Long estudianteId, @Param("carreraId") Long carreraId);
}
}
