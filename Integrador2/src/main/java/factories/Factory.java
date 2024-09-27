package factories;

import daos.CarreraDao;
import daos.EstudianteDao;
import daos.InscripcionDao;

public abstract class Factory {
    public static final int MYSQL_JDBC = 1;


    public abstract CarreraDao getCarreraDAO();
    public abstract EstudianteDao getEstudianteDAO();
    public abstract InscripcionDao getInscripcionDAO();

    public static Factory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC:
                return new MySqlFactory();
            default:
                return null;
        }
    }
}
