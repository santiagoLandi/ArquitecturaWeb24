package dao;

import jakarta.persistence.EntityManager;
import model.Persona;
import model.Socio;
import model.Turno;
import org.hibernate.Hibernate;

import java.util.ArrayList;
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

    @Override
    public List<Persona> getSociosPorTurno(Turno turno) {
        try(EntityManager em = connect.getFactory().createEntityManager()){
            em.getTransaction().begin();
            if (!em.contains(turno)) {
                turno = em.merge(turno);
            }
            Hibernate.initialize(turno.getJugadores());
            List<Persona> jugadores = (ArrayList<Persona>) turno.getJugadores();
            List<Persona>sociosEnElTurno = new ArrayList<>();
            // Verificar si los jugadores son socios
            for (Persona persona : jugadores) {
                if (this.isSocio(persona, em)) {  // Verificar si la persona es socio
                    sociosEnElTurno.add(persona);
                }
            }
            /*
            // Filtrar y obtener los socios de la lista de jugadores
            List<Persona> sociosEnElTurno = jugadores.stream()
            .filter(this::IsSocio)  // Verificar si la persona es socio
            .collect(Collectors.toList()); // Recoger los resultados en una lista
             */
            return sociosEnElTurno;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isSocio(Persona persona, EntityManager em) {
        // Verificar si la persona es socio usando una única consulta
        Socio buscado = em.find(Socio.class, persona.getIdPersona());
        return buscado != null;
    }
}
