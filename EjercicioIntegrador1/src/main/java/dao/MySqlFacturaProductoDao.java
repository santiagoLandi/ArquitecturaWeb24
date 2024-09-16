package dao;

import entidades.FacturaProducto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlFacturaProductoDao implements FacturaProductoDao {
    private final Connection conexion;

    public MySqlFacturaProductoDao(ConeccionManager coneccionManager) throws SQLException {
        this.conexion=coneccionManager.getConnection();
    }

    @Override
    public void createTable() throws SQLException {
        conexion.setAutoCommit(false);
        String table = "CREATE TABLE facturaProducto (" +
                "idFactura int  NOT NULL," +
                "idProducto int  NOT NULL," +
                "cantidad int  NOT NULL," +
                "CONSTRAINT factura_producto_pk PRIMARY KEY (idFactura, idProducto)" +
                ");";
        String addConstraint1 = "ALTER TABLE facturaProducto " +
                "ADD CONSTRAINT Factura_Producto_Factura " +
                "FOREIGN KEY (idFactura) " +
                "REFERENCES factura (idFactura)" +
                ";";
        String addConstraint2 ="ALTER TABLE facturaProducto " +
                "ADD CONSTRAINT Factura_Producto_Producto " +
                "FOREIGN KEY (idProducto) " +
                "REFERENCES producto (idProducto)" +
                ";";
        conexion.prepareStatement(table).execute();
        conexion.prepareStatement(addConstraint1).execute();
        conexion.prepareStatement(addConstraint2).execute();
        conexion.commit();

    }

    @Override
    public void readCSV(String path) {
        CSVParser parser;
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
            for (CSVRecord row : parser){
                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idProducto = Integer.parseInt(row.get("idProducto"));
                int cantidad = Integer.parseInt(row.get("cantidad"));
                FacturaProducto facturaProducto = new FacturaProducto(idFactura, idProducto, cantidad);
                this.addFacturaProducto(facturaProducto);
            }
        } catch (IOException|SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addFacturaProducto(FacturaProducto facturaProducto) throws SQLException {
        conexion.setAutoCommit(false);
        String sql="INSERT INTO facturaProducto VALUES(?,?,?)";
        PreparedStatement ps=conexion.prepareStatement(sql);
        ps.setInt(1, facturaProducto.getIdFactura());
        ps.setInt(2, facturaProducto.getIdProducto());
        ps.setInt(3, facturaProducto.getCantidad());
        ps.executeUpdate();
        ps.close();
        conexion.commit();

    }

}

