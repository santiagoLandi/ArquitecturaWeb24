package com.intellij;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class DireccionImp implements Dao<Direccion> {
    private EntityManagerFactory factory;
    private EntityManager em;

    public DireccionImp() {
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
    public List<Direccion> getAll() {
        try {
            this.iniciarConexion();
            List<Direccion> direcciones = em.createQuery("SELECT d FROM Direccion d", Direccion.class).getResultList();

            return direcciones;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(Direccion dir) {
        try {
            this.iniciarConexion();
            this.em.merge(dir);
            this.cerrarConexion();
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public boolean update(int id, String string) {
        try{
            this.iniciarConexion();
            Direccion direccion = em.find(Direccion.class, id);
            direccion.setCalle(string);
            this.em.merge(direccion);
            this.cerrarConexion();
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        try{
            this.iniciarConexion();
            this.em.remove(em.find(Direccion.class, id));
            this.cerrarConexion();
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Direccion getObject(int id) {
        try {
            this.iniciarConexion();
            Direccion direccion = em.find(Direccion.class, id);
            this.cerrarConexion();
            return direccion;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
