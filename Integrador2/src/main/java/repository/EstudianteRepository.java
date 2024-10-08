package repository;

import dtos.EstudianteDTO;
import entities.Carrera;
import entities.Estudiante;
import entities.Inscripcion;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

public class EstudianteRepository implements Repository<Estudiante> {
    private EntityManager em;
    private static EstudianteRepository instance;

    private EstudianteRepository(EntityManager em) {
        this.em = em;
    }

    public static EstudianteRepository getInstance(EntityManager em) {
        if (instance == null)
            instance = new EstudianteRepository(em);
        return instance;
    }

    @Override
    // a) Dar de alta un estudiante
    public void insert(Estudiante estudiante) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            em.persist(estudiante);
            transaction.commit();
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al insertar estudiante! " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Estudiante selectById(int id) {
        try {
            return em.createQuery("SELECT e FROM Estudiante e WHERE e.id = :id", Estudiante.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (PersistenceException e) {
            System.out.println("Error al obtener estudiante por id! " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Estudiante> selectAll() {
        try {
            return em.createQuery("SELECT e FROM Estudiante e", Estudiante.class).getResultList();
        } catch (PersistenceException e) {
            System.out.println("Error al obtener estudiantes! " + e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean update(Estudiante estudiante) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {

            Estudiante estudianteExistente = em.find(Estudiante.class, estudiante.getId());

            if (estudianteExistente != null) {

                estudianteExistente.setNombres(estudiante.getNombres());
                estudianteExistente.setApellido(estudiante.getApellido());
                estudianteExistente.setGenero(estudiante.getGenero());
                estudianteExistente.setDni(estudiante.getDni());
                estudianteExistente.setCiudadResidencia(estudiante.getCiudadResidencia());
                estudianteExistente.setLibretaUniv(estudiante.getLibretaUniv());

                List<Inscripcion> inscripcionesExistentes = estudianteExistente.getInscripciones();

                for (Inscripcion inscripcion : inscripcionesExistentes) {
                    if (!estudiante.getInscripciones().contains(inscripcion)) {
                        estudianteExistente.removeInscripcion(inscripcion);
                    }
                }

                for (Inscripcion inscripcion : estudiante.getInscripciones()) {
                    if (!inscripcionesExistentes.contains(inscripcion)) {
                        estudianteExistente.addInscripcion(inscripcion);
                    }
                }

                em.merge(estudianteExistente);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                System.out.println("Estudiante no encontrado para actualizar!");
                return false;
            }
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al actualizar estudiante! " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            Estudiante estudiante = em.find(Estudiante.class, id);

            if (estudiante != null) {
                em.remove(estudiante);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                System.out.println("El estudiante con id=" + id + " no existe!");
                return false;
            }
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al eliminar estudiante! " + e.getMessage());
            return false;
        }
    }

    // c) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple -> Por nombre
    public List<EstudianteDTO> obtenerEstudiantesOrdenadosPorNombre() {
        try {
            return em.createQuery("SELECT new dtos.EstudianteDTO(e.nombres,e.apellido,e.anioNacimiento, " +
                    "e.genero,e.dni,e.ciudadResidencia,e.libretaUniv) " +
                    "FROM Estudiante e ORDER BY e.nombres", EstudianteDTO.class).getResultList();
        } catch (PersistenceException e) {
            System.out.println("Error al obtener estudiantes ordenados por nombre! " + e.getMessage());
            throw e;
        }
    }

    // d) Recuperar un estudiante en base a su número de libreta universitaria
    public EstudianteDTO obtenerEstudiantePorLu(long lu) {
        try {
            return em.createQuery("SELECT new dtos.EstudianteDTO(e.nombres,e.apellido,e.anioNacimiento," +
                                     "e.genero,e.dni,e.ciudadResidencia,e.libretaUniv) " +
                                     "FROM Estudiante e " +
                                     "WHERE e.libretaUniv = :lu", EstudianteDTO.class)
                    .setParameter("lu", lu)
                    .getSingleResult();
        } catch (PersistenceException e) {
            System.out.println("Error al obtener estudiante por libreta universitaria! " + e.getMessage());
            throw e;
        }
    }

    // e) Recuperar todos los estudiantes en base a su género
    public List<EstudianteDTO> obtenerEstudiantesPorGenero(String genero) {

        try {
            return em.createQuery("SELECT new dtos.EstudianteDTO(e.nombres,e.apellido,e.anioNacimiento," +
                                     "e.genero,e.dni,e.ciudadResidencia,e.libretaUniv) " +
                                     "FROM Estudiante e " +
                                     "WHERE e.genero = :genero", EstudianteDTO.class)
                    .setParameter("genero", genero)
                    .getResultList();
        } catch (PersistenceException e) {
            System.out.println("Error al obtener estudiante por su genero! " + e.getMessage());
            throw e;
        }
    }

    // g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia
    public List<EstudianteDTO> recuperarEstudiantesPorCarreraYCiudad(Carrera carrera, String ciudadResidencia) {
        try {
            return em.createQuery(
                                 "SELECT new dtos.EstudianteDTO(e.nombres,e.apellido,e.anioNacimiento," +
                                    "e.genero,e.dni,e.ciudadResidencia,e.libretaUniv) " +
                                    "FROM Estudiante e JOIN e.inscripciones i " +
                                    "WHERE i.carrera = :carrera AND e.ciudadResidencia = :ciudad", EstudianteDTO.class)
                    .setParameter("carrera", carrera)
                    .setParameter("ciudad", ciudadResidencia)
                    .getResultList();
        } catch (PersistenceException e) {
            System.out.println("Error al obtener estudiantes por carrera y residencia! " + e.getMessage());
            throw e;
        }
    }
    //Recuperar un estudiante en base a su nombre
    public EstudianteDTO selectByName(String nombre) {
        try{
            return em.createQuery("SELECT new dtos.EstudianteDTO(e.nombres,e.apellido,e.anioNacimiento," +
                                     "e.genero,e.dni,e.ciudadResidencia,e.libretaUniv) " +
                                     "FROM Estudiante e " +
                                     "WHERE e.nombres = :nombre", EstudianteDTO.class)
                    .setParameter("nombre",nombre)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Error al obtener estudiante por nombre! " + e.getMessage());
            throw e;
        }
    }


}
