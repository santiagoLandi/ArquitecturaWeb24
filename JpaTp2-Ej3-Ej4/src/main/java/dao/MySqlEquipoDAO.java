package dao;

import jakarta.persistence.EntityManager;
import model.Equipo;
import model.Torneo;

import java.util.List;

public class MySqlEquipoDAO implements EquipoDAO {
    private Connect conn;

    public MySqlEquipoDAO(Connect conn) {
        this.conn = conn;
    }

    @Override
    public void insertEquipo(Equipo equipo) {
        try(EntityManager em= conn.getFactory().createEntityManager()){
            em.getTransaction().begin();
            em.persist(equipo);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEquipo(int id, String nombreEquipo, String entidadRepre, Torneo torneo) {
        try(EntityManager em= conn.getFactory().createEntityManager()){
            em.getTransaction().begin();
            Equipo equipo= em.find(Equipo.class,id);
            equipo.setNombreEquipo(nombreEquipo);
            equipo.setEntidadRepresentada(entidadRepre);
            equipo.setTorneo(torneo);
            em.merge(equipo);
            em.getTransaction().commit();
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteEquipo(Equipo equipo) {
        try(EntityManager em= conn.getFactory().createEntityManager()){
            em.getTransaction().begin();
            em.remove(equipo);
            em.getTransaction().commit();
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Equipo getEquipo(int id) {
        try(EntityManager em= conn.getFactory().createEntityManager()){
            return em.find(Equipo.class,id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Equipo> getEquipos() {
        try(EntityManager em= conn.getFactory().createEntityManager()){
            return em.createQuery("from Equipo", Equipo.class).getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
