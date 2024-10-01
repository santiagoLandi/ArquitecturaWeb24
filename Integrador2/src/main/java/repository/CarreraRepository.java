package repository;

import dtos.ReporteCarreraDTO;
import entities.Carrera;
import entities.Inscripcion;

import javax.persistence.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CarreraRepository implements Repository<Carrera> {
    private EntityManager em;
    private static CarreraRepository instance;


    private CarreraRepository(EntityManager em) {
        this.em = em;
    }

    public static CarreraRepository getInstance(EntityManager em) {
        if (instance == null)
            instance = new CarreraRepository(em);
        return instance;
    }


    @Override
    public void insert(Carrera carrera) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            em.persist(carrera);
            transaction.commit();
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al insertar carrera! " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Carrera selectById(int id) {
        try {
            return em.createQuery("SELECT c FROM Carrera c WHERE c.id = :id", Carrera.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (PersistenceException e) {
            System.out.println("Error al obtener carrera por id! " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Carrera> selectAll() {
        try {
            return em.createQuery("SELECT c FROM Carrera c", Carrera.class).getResultList();
        } catch (PersistenceException e) {
            System.out.println("Error al obtener carreras! " + e.getMessage());
            throw e;
        }
    }

    // Al tener cascade = CascadeType.ALL, cualquier operación realizada en la entidad Carrera
    // (insertar, actualizar, eliminar) también afectará automáticamente a las entidades relacionadas
    @Override
    public boolean update(Carrera carrera) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            // Buscar si la carrera existe
            Carrera carreraExistente = em.find(Carrera.class, carrera.getId());

            if (carreraExistente != null) {
                // Actualizar los campos necesarios
                carreraExistente.setNombre(carrera.getNombre());

                // Actualizar la lista de inscripciones
                // Eliminar las inscripciones antiguas que no están en la nueva lista
                List<Inscripcion> inscripcionesExistentes = carreraExistente.getInscripciones();

                for (Inscripcion inscripcion : inscripcionesExistentes) {
                    if (!carrera.getInscripciones().contains(inscripcion)) {
                        carreraExistente.removeInscripcion(inscripcion);
                    }
                }

                // Agregar nuevas inscripciones que no están en la lista existente
                for (Inscripcion inscripcion : carrera.getInscripciones()) {
                    if (!inscripcionesExistentes.contains(inscripcion)) {
                        carreraExistente.addInscripcion(inscripcion);
                    }
                }

                // Persistir los cambios
                em.merge(carreraExistente);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                System.out.println("Carrera no encontrada para actualizar!");
                return false;
            }
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al actualizar carrera! " + e.getMessage());
            return false; // Retornar false en caso de error
        }
    }

    // Al tener cascade = CascadeType.ALL, cualquier operación realizada en la entidad Carrera
    // (insertar, actualizar, eliminar) también afectará automáticamente a las entidades relacionadas
    @Override
    public boolean delete(int id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            Carrera carrera = em.find(Carrera.class, id);

            if (carrera != null) {
                em.remove(carrera);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                System.out.println("La carrera con id=" + id + " no existe!");
                return false;
            }
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al eliminar carrera! " + e.getMessage());
            return false;
        }
    }

    /*
    public List<ReporteCarreraDTO> generarReporteCarreras() {
        try {
            String jpql = "SELECT new dtos.ReporteCarreraDTO(c.nombre, " +
                    "i.anioInscripcion, " +
                    "i.anioEgreso, " +
                    "(SELECT COUNT(i1) FROM Inscripcion i1 WHERE i1.anioEgreso IS NULL AND i1.carrera = c), " + // Inscripciones sin egreso
                    "(SELECT COUNT(i2) FROM Inscripcion i2 WHERE i2.anioEgreso IS NOT NULL AND i2.carrera = c)) " + // Inscripciones con egreso
                    "FROM Inscripcion i " +
                    "JOIN i.carrera c " +
                    "JOIN i.estudiante e " +
                    "ORDER BY c.nombre ASC, YEAR(i.anioInscripcion) ASC, YEAR(i.anioEgreso) ASC";

            TypedQuery<ReporteCarreraDTO> query = em.createQuery(jpql, ReporteCarreraDTO.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al generar reporte de carreras: " + e.getMessage());
            return null;
        }
    }
    */
    /*
    public List<ReporteCarreraDTO> generarReporteCarreras() {
        try {
            /*
            String jpql = "SELECT new dtos.ReporteCarreraDTO(c.nombre, " +
                    "i.anioInscripcion, " +
                    "(SELECT COUNT(i1) FROM Inscripcion i1 WHERE i1.anioInscripcion = i.anioInscripcion AND i1.carrera = c), " + // Cantidad de inscriptos por año
                    "(SELECT COUNT(i2) FROM Inscripcion i2 WHERE i2.anioEgreso = i.anioInscripcion AND i2.carrera = c) ) " + // Cantidad de egresados por año
                    "FROM Inscripcion i " +
                    "JOIN i.carrera c " +
                    "ORDER BY c.nombre ASC, i.anioInscripcion ASC";

            String sql ="SELECT c.nombre AS nombreCarrera, " +
                    "EXTRACT(YEAR FROM i.anioInscripcion) AS anio, " +
                    "COUNT(i) AS cantInscriptos, " +
                    "SUM(CASE WHEN i.graduado IS NOT NULL THEN 1 ELSE 0 END) AS cantEgresados " +
                    "FROM Inscripcion i " +
                    "JOIN i.carrera c " +
                    "GROUP BY c.nombre, anio " +
                    "ORDER BY c.nombre ASC, 2 ASC";
            Query query= em.createQuery(sql);
            List<Object[]> results = query.getResultList();

            return results.stream()
                    .map(result -> new ReporteCarreraDTO(
                            (String) result[0],
                            (Integer) result[1], // Año
                            (Long) result[2],  // Cantidad de inscriptos
                            (Long) result[3]   // Cantidad de egresados
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error al generar reporte de carreras: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }

    }
    */
    public List<ReporteCarreraDTO> getInscriptosYEgresadosPorAnio() {
        try {
            // Consulta para inscriptos
            String jpqlInscriptos = "SELECT c.nombre AS nombreCarrera, i.anioInscripcion AS anio, COUNT(i) AS cantInscriptos " +
                    "FROM Inscripcion i JOIN i.carrera c " +
                    "GROUP BY c.nombre, i.anioInscripcion " +
                    "ORDER BY c.nombre ASC, i.anioInscripcion ASC";
            Query queryInscriptos = em.createQuery(jpqlInscriptos);
            List<Object[]> inscriptosResults = queryInscriptos.getResultList();

            // Consulta para egresados
            String jpqlEgresados = "SELECT c.nombre AS nombreCarrera, i.anioEgreso AS anio, COUNT(i) AS cantEgresados " +
                    "FROM Inscripcion i JOIN i.carrera c " +
                    "WHERE i.anioEgreso IS NOT NULL " +
                    "GROUP BY c.nombre, i.anioEgreso " +
                    "ORDER BY c.nombre ASC, i.anioEgreso ASC";
            Query queryEgresados = em.createQuery(jpqlEgresados);
            List<Object[]> egresadosResults = queryEgresados.getResultList();

            // Combinar resultados
            Map<String, Map<Integer, ReporteCarreraDTO>> reporteMap = new HashMap<>();

            for (Object[] result : inscriptosResults) {
                String nombreCarrera = (String) result[0];
                int anio = (Integer) result[1];
                long cantInscriptos = (Long) result[2];

                reporteMap
                        .computeIfAbsent(nombreCarrera, k -> new HashMap<>())
                        .put(anio, new ReporteCarreraDTO(nombreCarrera, anio, cantInscriptos, 0));
            }

            for (Object[] result : egresadosResults) {
                String nombreCarrera = (String) result[0];
                int anio = (Integer) result[1];
                long cantEgresados = (Long) result[2];

                reporteMap
                        .computeIfAbsent(nombreCarrera, k -> new HashMap<>())
                        .computeIfAbsent(anio, k -> new ReporteCarreraDTO(nombreCarrera, anio, 0, 0))
                        .setCantidadGraduados(cantEgresados);
            }

            return reporteMap.values().stream()
                    .flatMap(map -> map.values().stream())
                    .sorted(Comparator.comparing(ReporteCarreraDTO::getNombreCarrera)
                            .thenComparing(ReporteCarreraDTO::getAnio))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error al generar reporte de carreras: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
}
