package factories;

import repository.CarreraRepository;
import repository.EstudianteRepository;
import repository.InscripcionRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MySqlRepositoryFactory extends RepositoryFactory {
    private static final String PERSISTENCE_UNIT_NAME = "Integrador2";

    @Override
    public CarreraRepository getCarreraRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        return CarreraRepository.getInstance(em);
    }

    @Override
    public EstudianteRepository getEstudianteRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        return  EstudianteRepository.getInstance(em);
    }

    @Override
    public InscripcionRepository getInscripcionRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        return InscripcionRepository.getInstance(em);
    }
}
