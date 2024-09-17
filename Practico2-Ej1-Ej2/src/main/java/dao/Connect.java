package dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class Connect {
    private static volatile EntityManagerFactory emf;
    private static Connect conn;

    private Connect() {
    }

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
                    emf = Persistence.createEntityManagerFactory("jpaTP2");
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
