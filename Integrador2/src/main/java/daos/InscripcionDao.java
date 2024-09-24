package daos;

import entidades.Carrera;
import entidades.Estudiante;
import entidades.Inscripcion;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

public class InscripcionDao implements Dao<Inscripcion> {
    private EntityManager em;

    public InscripcionDao(EntityManager em) {
        this.em = em;
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
    public List<Object[]> recuperarCarrerasOrdenadasPorCantidadInscriptos() {
        try {
            return em.createQuery(
                            "SELECT i.carrera.nombre, COUNT(i) AS inscriptos " +
                                    "FROM Inscripcion i " +
                                    "GROUP BY i.carrera.nombre " +
                                    "HAVING COUNT(i) > 0 " + // Solo incluir carreras con al menos un inscripto
                                    "ORDER BY COUNT(i) DESC", Object[].class)
                    .getResultList();
        } catch (PersistenceException e) {
            System.out.println("Error al obtener carreras con inscriptos! " + e.getMessage());
            throw e;
        }
    }

    // g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia
    public List<Estudiante> recuperarEstudiantesPorCarreraYCiudad(Carrera carrera, String ciudadResidencia) {
        try {
            return em.createQuery(
                            "SELECT e FROM Inscripcion i JOIN i.estudiante e WHERE i.carrera = :carrera AND e.ciudadResidencia = :ciudad", Estudiante.class)
                    .setParameter("carrera", carrera)
                    .setParameter("ciudad", ciudadResidencia)
                    .getResultList();
        } catch (PersistenceException e) {
            System.out.println("Error al obtener estudiantes por carrera y residencia! " + e.getMessage());
            throw e;
        }
    }
}
