package dao;

import entidades.Factura;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlFacturaDao implements FacturaDao {
    private final Connection conexion;

    public MySqlFacturaDao(ConeccionManager coneccionManager) throws SQLException {
        this.conexion=coneccionManager.getConnection();
    }

    @Override
    public void createTable() throws SQLException {
        conexion.setAutoCommit(false);
        String table = "CREATE TABLE factura (" +
                "idFactura int  NOT NULL," +
                "idCliente int  NOT NULL," +
                "CONSTRAINT Factura_pk PRIMARY KEY (idFactura)" +
                ");";
        String addConstraint = "ALTER TABLE factura " +
                "ADD CONSTRAINT factura_cliente " +
                "FOREIGN KEY factura_cliente (idCliente)" +
                "REFERENCES cliente (idCliente)" +
                ";";
        conexion.prepareStatement(table).execute();
        conexion.prepareStatement(addConstraint).execute();
        conexion.commit();
    }

    @Override
    public void readCSV(String path) {
        CSVParser parser;
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
            for (CSVRecord row : parser){
                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idCliente = Integer.parseInt(row.get("idCliente"));
                Factura factura = new Factura(idFactura, idCliente);
                this.addFactura(factura);
            }
        } catch (IOException|SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addFactura(Factura factura) throws SQLException {
        conexion.setAutoCommit(false);
        String sql="INSERT INTO factura (idFactura,idCliente) VALUES (?,?)";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, factura.getIdFactura());
        ps.setInt(2, factura.getIdCliente());
        ps.execute();
        ps.close();
        conexion.commit();
    }

}

