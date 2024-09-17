package dao;

import jakarta.persistence.EntityManager;
import model.Direccion;
import model.Persona;

import java.util.List;

public class MysqlPersonaDAO implements PersonaDAO {
    private Connect connect;

    public MysqlPersonaDAO(Connect connect) {
        this.connect = connect;
    }

    @Override
    public void insert(Persona persona) {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            em.getTransaction().begin();
            em.persist(persona);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Persona findById(int id) {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            return em.find(Persona.class, id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Persona> findAll() {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            return em.createQuery("from Persona", Persona.class).getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, String nombre, int edad, Direccion direccion) {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            em.getTransaction().begin();
            Persona persona = em.find(Persona.class, id);
            persona.setNombre(nombre);
            persona.setEdad(edad);
            persona.setDireccion(direccion);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(Persona persona) {
        try (EntityManager em = connect.getFactory().createEntityManager()) {
            em.getTransaction().begin();
            em.remove(em.contains(persona) ? persona : em.merge(persona));
            em.getTransaction().commit();
        }
    }
}
