package dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConeccionManager {
    Connection getConnection() throws SQLException;
}
