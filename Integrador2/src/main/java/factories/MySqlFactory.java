package factories;

import daos.CarreraDao;
import daos.Dao;
import daos.EstudianteDao;
import daos.InscripcionDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MySqlFactory extends Factory {
    private static final String PERSISTENCE_UNIT_NAME = "Integrador2";

    @Override
    public Dao getCarreraDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        return new CarreraDao(em);
    }

    @Override
    public Dao getEstudianteDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        return new EstudianteDao(em);
    }

    @Override
    public Dao getInscripcionDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        return new InscripcionDao(em);
    }
}
