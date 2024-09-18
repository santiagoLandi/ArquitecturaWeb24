package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Persona;
import model.Turno;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MysqlTurnoDAO implements TurnoDAO {
    private Connect connect;

    public MysqlTurnoDAO(Connect connect) {
        this.connect = connect;
    }

    @Override
    public void insert(Turno turno) {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            em.getTransaction().begin();
            em.persist(turno);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    public void agregarJugador(Turno turno, Persona persona) {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            em.getTransaction().begin();
            // Reatachar las entidades "detached"
            Turno managedTurno = em.merge(turno);
            Persona managedPersona = em.merge(persona);
            // Verificar si la persona ya está en la lista de jugadores
            if (!managedTurno.getJugadores().contains(managedPersona)) {
                // Agregar la persona al turno y viceversa
                managedTurno.addJugadoresATurno(managedPersona);
            }
            // Guardar las entidades
            em.merge(managedTurno);
            em.merge(managedPersona);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public List<Persona> getJugadoresPorTurno(Turno turno) {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            em.getTransaction().begin();
            // Asegurarse de que el turno esté en el contexto de persistencia
            if (!em.contains(turno)) {
                turno = em.merge(turno);
            }
            // Forzar la inicialización de la colección jugadores
            Hibernate.initialize(turno.getJugadores());
            ArrayList<Persona> jugadores = (ArrayList<Persona>) turno.getJugadores();
            em.getTransaction().commit();
            return jugadores;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    public Turno obtenerTurnoConJugadores(Long turnoId) {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            em.getTransaction().begin();
            Turno turno = em.find(Turno.class, turnoId);
            // Inicializar la lista de jugadores si es necesario
            turno.getJugadores().size(); // Esto fuerza la carga de la lista si es Lazy
            return turno;
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    public int findByDate(LocalDateTime fecha) {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            em.getTransaction().begin();
            Turno turno= em.createQuery("SELECT t from Turno t where t.fecha = :fecha ", Turno.class).setParameter("fecha",fecha).getSingleResult();
            return turno.getIdTurno();
        }catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Turno findById(int id) {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            return em.find(Turno.class, id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Turno> findAll() {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            em.getTransaction().begin();
            return em.createQuery("from Turno", Turno.class).getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getIdFromTurno(Turno turno){
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Turno> query = em.createQuery(
                    "SELECT t FROM Turno t WHERE t.fecha = :fecha", Turno.class);
            query.setParameter("fecha", turno.getFecha());
            List<Turno> turnos = query.getResultList();
            if (turnos.isEmpty()) {
                // Si no se encuentra el turno, puedes manejarlo como prefieras, lanzar una excepción o devolver un valor.
                throw new RuntimeException("No se encontró un turno con esa fecha");
            }

            return turnos.get(0).getIdTurno();
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    public int getIdFromTurno(Turno turno) {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            em.getTransaction().begin();
            if (!em.contains(turno)) {
                turno = em.merge(turno);
            }
            em.getTransaction().commit();
            return turno.getIdTurno();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    */
    @Override
    public List<Persona> findAllById(int id) {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            em.getTransaction().begin();
            Turno turno = em.createQuery("SELECT t FROM Turno t JOIN FETCH t.jugadores WHERE t.idTurno = :id", Turno.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return turno.getJugadores();
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, LocalDateTime fecha) {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            em.getTransaction().begin();
            Turno turno = em.find(Turno.class, id);
            turno.setFecha(fecha);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(Turno turno) {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            em.getTransaction().begin();
            em.remove(em.contains(turno) ? turno : em.merge(turno));
            em.getTransaction().commit();
        }
    }
}
