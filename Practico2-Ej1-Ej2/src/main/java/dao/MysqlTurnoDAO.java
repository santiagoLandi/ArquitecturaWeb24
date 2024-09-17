package dao;

import jakarta.persistence.EntityManager;
import model.Persona;
import model.Turno;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
            return em.createQuery("SELECT t FROM Turno t WHERE t.idTurno = :id", Turno.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Turno> findAll() {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            return em.createQuery("from Turno", Turno.class).getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

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
