package dao;

import dto.ProductoDTO;
import entidades.Producto;

import java.sql.SQLException;

public interface ProductoDao {
    void createTable() throws SQLException;
    void readCSV(String path);
    ProductoDTO obtenerProductoConMasRecaudacion() throws SQLException;
}
