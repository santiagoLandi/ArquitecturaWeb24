package factories;

import repository.CarreraRepository;
import repository.EstudianteRepository;
import repository.InscripcionRepository;

public abstract class RepositoryFactory {
    public static final int MYSQL_JDBC = 1;


    public abstract CarreraRepository getCarreraRepository();
    public abstract EstudianteRepository getEstudianteRepository();
    public abstract InscripcionRepository getInscripcionRepository();

    public static RepositoryFactory getRepositoryFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC:
                return new MySqlRepositoryFactory();
            default:
                return null;
        }
    }
}
