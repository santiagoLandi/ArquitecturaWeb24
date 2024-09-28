package factories;

import repository.CarreraRepository;
import repository.EstudianteRepository;
import repository.InscripcionRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MySqlFactory extends Factory {
    private static final String PERSISTENCE_UNIT_NAME = "Integrador2";

    @Override
    public CarreraRepository getCarreraDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        return new CarreraRepository(em);
    }

    @Override
    public EstudianteRepository getEstudianteDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        return new EstudianteRepository(em);
    }

    @Override
    public InscripcionRepository getInscripcionDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        return new InscripcionRepository(em);
    }
}
