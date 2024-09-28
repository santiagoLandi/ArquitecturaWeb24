package factories;

import repository.CarreraRepository;
import repository.EstudianteRepository;
import repository.InscripcionRepository;

public abstract class Factory {
    public static final int MYSQL_JDBC = 1;


    public abstract CarreraRepository getCarreraDAO();
    public abstract EstudianteRepository getEstudianteDAO();
    public abstract InscripcionRepository getInscripcionDAO();

    public static Factory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC:
                return new MySqlFactory();
            default:
                return null;
        }
    }
}
