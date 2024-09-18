package dao;

import jakarta.persistence.EntityManager;
import model.Direccion;

import java.util.List;

public class MysqlDireccionDAO implements DireccionDAO{
    private Connect connect;

    public MysqlDireccionDAO(Connect connect) {
        this.connect = connect;
    }
    @Override
    public void insert(Direccion d) {
        try (EntityManager em = connect.getFactory().createEntityManager()){
            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Direccion findById(int id) {
        try(EntityManager em = connect.getFactory().createEntityManager()){
            return em.find(Direccion.class, id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Direccion> findAll() {
        try(EntityManager em = connect.getFactory().createEntityManager()){
            return em.createQuery("from Direccion", Direccion.class).getResultList();
        }
    }

    @Override
    public void update(int id, String calle, String ciudad) {
        try (EntityManager em = connect.getFactory().createEntityManager()){
            em.getTransaction().begin();
            Direccion direccion = em.find(Direccion.class, id);
            direccion.setCalle(calle);
            direccion.setCiudad(ciudad);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(Direccion direccion) {
        try (EntityManager em = connect.getFactory().createEntityManager()){
            em.getTransaction().begin();
            em.remove(em.contains(direccion) ? direccion : em.merge(direccion));
            em.getTransaction().commit();
        }
    }
}
