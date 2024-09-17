package dao;

import jakarta.persistence.EntityManager;
import model.Socio;

import java.util.List;

public class MysqlSocioDAO implements SocioDAO {
    private Connect connect;

    public MysqlSocioDAO(Connect connect) {
        this.connect = connect;
    }
    @Override
    public void insert(Socio s) {
        try(EntityManager em = connect.getFactory().createEntityManager()){
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Socio findById(int id) {
        try(EntityManager em = connect.getFactory().createEntityManager()){
            return em.find(Socio.class, id);
        }
    }

    @Override
    public List<Socio> findAll() {
        try (EntityManager em = connect.getFactory().createEntityManager()){
            return em.createQuery("from Socio", Socio.class).getResultList();
        }
    }

    @Override
    public void update(int id,String tipo) {
        try (EntityManager em = connect.getFactory().createEntityManager()){
            em.getTransaction().begin();
            Socio socio = em.find(Socio.class, id);
            socio.setTipo(tipo);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(Socio s) {
        try(EntityManager em = connect.getFactory().createEntityManager()){
            em.getTransaction().begin();
            em.remove(s);
            em.getTransaction().commit();
        }
    }
}
