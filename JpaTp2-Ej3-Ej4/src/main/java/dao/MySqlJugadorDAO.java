package dao;

import jakarta.persistence.EntityManager;
import model.Equipo;
import model.Jugador;

import java.util.List;

public class MySqlJugadorDAO implements JugadorDAO {
    private Connect conn;

    public MySqlJugadorDAO(Connect conn) {
        this.conn = conn;
    }

    @Override
    public void insertJugador(Jugador jugador) {
        try(EntityManager em=conn.getFactory().createEntityManager()){
            em.getTransaction().begin();
            em.persist(jugador);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateJugador(int id, String nombreApellido, String posicion, Equipo equipo) {
        try(EntityManager em=conn.getFactory().createEntityManager()){
            em.getTransaction().begin();
            Jugador jugador=em.find(Jugador.class, id);
            jugador.setNombreApellido(nombreApellido);
            jugador.setPosicion(posicion);
            jugador.setEquipo(equipo);
            em.getTransaction().commit();
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteJugador(Jugador jugador) {
        try(EntityManager em=conn.getFactory().createEntityManager()){
            em.getTransaction().begin();
            em.remove(jugador);
            em.getTransaction().commit();
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Jugador getJugador(int id) {
        try(EntityManager em=conn.getFactory().createEntityManager()){
            return em.find(Jugador.class, id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Jugador> getJugadores() {
        try(EntityManager em=conn.getFactory().createEntityManager()){
            return em.createQuery("from Jugador", Jugador.class).getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
