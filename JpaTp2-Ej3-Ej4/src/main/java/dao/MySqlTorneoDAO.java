package dao;

import jakarta.persistence.EntityManager;
import model.Equipo;
import model.Torneo;

import java.util.List;

public class MySqlTorneoDAO implements TorneoDAO {
    private Connect conn;

    public MySqlTorneoDAO(Connect conn) {
        this.conn = conn;
    }

    @Override
    public void insertTorneo(Torneo torneo) {
        try(EntityManager em=conn.getFactory().createEntityManager()){
            em.getTransaction().begin();
            em.persist(torneo);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTorneo(int id, String nombre) {
        try(EntityManager em=conn.getFactory().createEntityManager()){
            em.getTransaction().begin();
            Torneo torneo = em.find(Torneo.class, id);
            torneo.setNombreTorneo(nombre);
            em.getTransaction().commit();
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteTorneo(Torneo torneo) {
        try(EntityManager em=conn.getFactory().createEntityManager()){
            em.getTransaction().begin();
            em.remove(torneo);
            em.getTransaction().commit();
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public Torneo getTorneo(int id) {
        try(EntityManager em=conn.getFactory().createEntityManager()){
            return em.find(Torneo.class, id);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Torneo> getTorneos() {
        try(EntityManager em=conn.getFactory().createEntityManager()){
            return em.createQuery("from Torneo", Torneo.class).getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertEquipoEnTorneo(Equipo equipo, Torneo torneo) {
        try(EntityManager em=conn.getFactory().createEntityManager()){
            em.getTransaction().begin();
            Equipo equipo1=em.merge(equipo);
            Torneo torneo1=em.merge(torneo);
            if(torneo1.getEquiposParticipantes().contains(equipo1)){
                torneo1.addEquipo(equipo1);
            }
            em.merge(torneo1);
            em.merge(equipo1);

        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
