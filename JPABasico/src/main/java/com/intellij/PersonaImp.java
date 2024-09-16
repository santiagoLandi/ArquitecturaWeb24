package com.intellij;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaImp implements Dao<Persona> {

    private EntityManagerFactory factory;
    private EntityManager em;

    public PersonaImp() {
        this.factory= Persistence.createEntityManagerFactory("example");
        this.em = factory.createEntityManager();
    }
    public void iniciarConexion() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    public void cerrarConexion() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }
    public void close() {
        if (em.isOpen()) {
            em.close();
        }
        if (factory.isOpen()) {
            factory.close();
        }
    }
    @Override
    public List<Persona> getAll() {
       try {
           this.iniciarConexion();
           List<Persona> personas = em.createQuery(" from Persona ",Persona.class).getResultList();
           this.cerrarConexion();
           return personas;
       }catch (RuntimeException e){
           throw new RuntimeException(e);
       }
    }

    @Override
    public boolean insert(Persona persona) {
        try {
            this.iniciarConexion();
            em.persist(persona);
            this.cerrarConexion();
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);

        }
    }
    @Override
    public boolean update(int id, String nombre) {
        try{
            this.iniciarConexion();
            Persona persona = em.find(Persona.class, id);
            persona.setNombre(nombre);
            em.merge(persona);
            this.cerrarConexion();
            return true;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean delete(int id) {
        try{
            this.iniciarConexion();
            Persona persona = em.find(Persona.class, id);
            em.remove(persona);
            this.cerrarConexion();
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Persona getObject(int id) {
        try{
            this.iniciarConexion();
            Persona persona = em.find(Persona.class, id);
            this.cerrarConexion();
            return persona;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
