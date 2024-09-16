package services;


import dao.FacturaDao;

import java.sql.SQLException;

public class FacturaService {
    private FacturaDao facturaDao;

    public FacturaService(FacturaDao facturaDao) {
        this.facturaDao = facturaDao;
    }

    public void createTable() throws SQLException {
        this.facturaDao.createTable();
    }

    public void addFacturas(String path){
        this.facturaDao.readCSV(path);
    }



}
