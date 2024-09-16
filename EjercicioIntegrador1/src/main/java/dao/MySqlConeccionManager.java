package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConeccionManager implements ConeccionManager {

    @Override
    public Connection getConnection() throws SQLException {
        String uri="jdbc:mysql://localhost:3307/ejercicioIntegrador1";
        return DriverManager.getConnection(uri,"root","password");
    }
}
