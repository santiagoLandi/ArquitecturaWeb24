package dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Connection;

public class Connect {
    private static EntityManagerFactory emf;
    private static Connect conn;

    private Connect() {}

    public static Connect getConnection() {
        if (emf == null)
            synchronized (Connect.class) {
                if (emf == null) {
                    conn = new Connect();
                }
            }
        return conn;
    }

    public EntityManagerFactory getFactory() {
        if (emf == null) {
            synchronized (Connect.class) {
                if (emf == null) {
                    emf= Persistence.createEntityManagerFactory("jpaTP2Ej3Ej4");
                }
            }
        }
        return emf;
    }

    public void close() {
        if (emf != null) {
            emf.close();
        }
    }

}
