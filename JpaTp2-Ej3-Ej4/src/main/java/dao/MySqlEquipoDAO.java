package dao;

import jakarta.persistence.EntityManager;
import model.Equipo;
import model.Jugador;
import model.Torneo;
import org.hibernate.Hibernate;

import java.util.ArrayList;
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

    @Override
    public void insertJugadorEnEquipo(Jugador jugador, Equipo equipo) {
        try(EntityManager em= conn.getFactory().createEntityManager()){
            em.getTransaction().begin();
            Equipo team= em.merge(equipo);
            Jugador player= em.merge(jugador);

            if(!team.getJugadores().contains(player)){
                team.addJugador(player);
            }
            em.merge(team);
            em.merge(jugador);
            em.getTransaction().commit();
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Jugador> getJugadores(Equipo equipo) {
        try(EntityManager em= conn.getFactory().createEntityManager()){
            em.getTransaction().begin();
            Equipo team= em.merge(equipo);
            int i=team.getJugadores().size();
            return team.getJugadores();
            /*
            if(!em.contains(equipo)){
                em.merge(equipo);
            }
            Hibernate.initialize(equipo.getJugadores());
            ArrayList<Jugador>jugadores= (ArrayList<Jugador>)equipo.getJugadores();
            em.getTransaction().commit();
            return jugadores;
             */
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
