package dao;

import dto.ProductoDTO;
import entidades.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlProductoDao implements ProductoDao {
    private Connection conexion;

    public MySqlProductoDao(ConeccionManager con) throws SQLException {
        this.conexion=con.getConnection();
    }



    @Override
    public void createTable() throws SQLException {
        conexion.setAutoCommit(false);
        String table = "CREATE TABLE producto ( " +
                "idProducto int  NOT NULL, " +
                "nombre varchar(200)  NOT NULL, " +
                "valor float(15,7)  NOT NULL, " +
                "CONSTRAINT Producto_pk PRIMARY KEY (idProducto) " +
                ")";
        conexion.prepareStatement(table).execute();
        conexion.commit();

    }

    @Override
    public void readCSV(String path) {
        CSVParser parser;
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
            for (CSVRecord row : parser) {
                int id = Integer.parseInt(row.get("idProducto"));
                String nombre = row.get("nombre");
                float valor = Float.parseFloat(row.get("valor"));
                Producto prod = new Producto(id, nombre, valor);
                this.addProducto(prod);
            }
        } catch (IOException|SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
        public ProductoDTO obtenerProductoConMasRecaudacion () throws SQLException {
            ProductoDTO prod = null;
            String query= "SELECT p.nombre, p.valor , SUM(p.valor*fp.cantidad) AS totalRecaudacion " +
                          "FROM producto p " +
                          "JOIN facturaProducto fp USING(idProducto) " +
                          "GROUP BY p.idProducto " +
                          "ORDER BY totalRecaudacion DESC " +
                          "LIMIT 1";
            try{
                PreparedStatement ps = conexion.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    String nombre = rs.getString("nombre");
                    float valor = rs.getFloat("valor");
                    float totalVentas= rs.getFloat("totalRecaudacion");
                    prod = new ProductoDTO(nombre, valor,totalVentas);
                }
                ps.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return prod;
        }


        private void addProducto (Producto prod) throws SQLException {
            conexion.setAutoCommit(false);
            String sql = "INSERT INTO producto (idProducto,nombre,valor) VALUES (?,?,?)";
            PreparedStatement ps=conexion.prepareStatement(sql);
            ps.setInt(1, prod.getIdProducto());
            ps.setString(2, prod.getNombre());
            ps.setFloat(3, prod.getValor());
            ps.execute();
            ps.close();
            conexion.commit();
        }

    }