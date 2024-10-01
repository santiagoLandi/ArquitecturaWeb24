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

    @Override
    public boolean update(Carrera carrera) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {

            Carrera carreraExistente = em.find(Carrera.class, carrera.getId());

            if (carreraExistente != null) {

                carreraExistente.setNombre(carrera.getNombre());

                List<Inscripcion> inscripcionesExistentes = carreraExistente.getInscripciones();

                for (Inscripcion inscripcion : inscripcionesExistentes) {
                    if (!carrera.getInscripciones().contains(inscripcion)) {
                        carreraExistente.removeInscripcion(inscripcion);
                    }
                }

                for (Inscripcion inscripcion : carrera.getInscripciones()) {
                    if (!inscripcionesExistentes.contains(inscripcion)) {
                        carreraExistente.addInscripcion(inscripcion);
                    }
                }

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
            return false;
        }
    }


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
