package repository;

import dtos.CarreraConCantInscriptosDTO;
import entities.Carrera;
import entities.Estudiante;
import entities.Inscripcion;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class InscripcionRepository implements Repository<Inscripcion> {
    private EntityManager em;
    private static InscripcionRepository instance;

    private InscripcionRepository(EntityManager em) {
        this.em = em;
    }

    public static InscripcionRepository getInstance(EntityManager em) {
        if (instance == null)
            instance = new InscripcionRepository(em);
        return instance;

    }

    @Override
    public void insert(Inscripcion inscripcion) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            em.persist(inscripcion);
            transaction.commit();
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al insertar inscripción! " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Inscripcion selectById(int id) {
        try {
            return em.createQuery("SELECT i FROM Inscripcion i WHERE i.idInscripcion = :id", Inscripcion.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (PersistenceException e) {
            System.out.println("Error al obtener inscripcion por id! " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Inscripcion> selectAll() {
        try {
            return em.createQuery("SELECT i FROM Inscripcion i", Inscripcion.class).getResultList();
        } catch (PersistenceException e) {
            System.out.println("Error al obtener inscripciones! " + e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean update(Inscripcion inscripcion) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            // Buscar si la inscripción existe
            Inscripcion inscripcionExistente = em.find(Inscripcion.class, inscripcion.getId());

            if (inscripcionExistente != null) {
                // Actualizar los campos necesarios
                inscripcionExistente.setAnioInscripcion(inscripcion.getAnioInscripcion());
                inscripcionExistente.setAnioEgreso(inscripcion.getAnioEgreso());
                inscripcionExistente.setGraduado(inscripcion.isGraduado());
                inscripcionExistente.setCarrera(inscripcion.getCarrera());
                inscripcionExistente.setEstudiante(inscripcion.getEstudiante());

                // Persistir los cambios
                em.merge(inscripcionExistente);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                System.out.println("Inscripción no encontrada para actualizar!");
                return false;
            }
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al actualizar inscripción! " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            // Buscar la inscripción por ID
            Inscripcion inscripcion = em.find(Inscripcion.class, id);

            if (inscripcion != null) {
                // Eliminar la inscripción
                em.remove(inscripcion);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                System.out.println("La inscripción con id=" + id + " no existe!");
                return false;
            }
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al eliminar inscripción! " + e.getMessage());
            return false;
        }
    }

    // b) Matricular un estudiante en una carrera
    public void inscribirEstudianteEnCarrera(Integer anioInscripcion ,Carrera carrera,Estudiante estudiante ) {
        Inscripcion inscripcion = new Inscripcion(anioInscripcion,carrera, estudiante);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            em.persist(inscripcion);
            transaction.commit();
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al inscribir al estudiante en la carrera! " + e.getMessage());
            throw e;
        }
    }

    // f) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
    public List<CarreraConCantInscriptosDTO>listarCarrerasPorCantidadInscriptos() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            String jpql ="SELECT  new dtos.CarreraConCantInscriptosDTO (c.nombre, COUNT(*)) " +
                    "FROM Carrera c JOIN c.inscripciones i " +
                    "GROUP BY c.nombre " +
                    "ORDER BY 2 DESC ";
            TypedQuery<CarreraConCantInscriptosDTO> query = em.createQuery(jpql, CarreraConCantInscriptosDTO.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al generar reporte de carreras: " + e.getMessage());
        }
        return List.of();
    }


    public void fijarAnioDeGraduacion(Inscripcion i,Integer anioGraduacion) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try{
            Inscripcion buscada=em.find(Inscripcion.class, i.getId());
            System.out.println(buscada.getAnioInscripcion());
            if (buscada != null) {
                i.setAnioEgreso(anioGraduacion);
                em.merge(i);
            }
            transaction.commit();
        }catch (Exception e) {
            System.out.println("Error al setear anio graduacion en alumno: " + e.getMessage());
        }
    }

}
