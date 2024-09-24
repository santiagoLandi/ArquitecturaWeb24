package factories;

import daos.Dao;

public abstract class Factory {
    public static final int MYSQL_JDBC = 1;


    public abstract Dao getCarreraDAO();
    public abstract Dao getEstudianteDAO();
    public abstract Dao getInscripcionDAO();

    public static Factory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC:
                return new MySqlFactory();
            default:
                return null;
        }
    }
}
