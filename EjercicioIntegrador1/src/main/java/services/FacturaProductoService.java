package services;

import dao.FacturaProductoDao;

import java.sql.SQLException;

public class FacturaProductoService {
    private FacturaProductoDao facturaProductoDao;

    public FacturaProductoService(FacturaProductoDao facturaProductoDao) {
        this.facturaProductoDao = facturaProductoDao;
    }

    public void createTable() throws SQLException {
        this.facturaProductoDao.createTable();
    }

    public void addFacturaProducto(String path){
        this.facturaProductoDao.readCSV(path);
    }
}
