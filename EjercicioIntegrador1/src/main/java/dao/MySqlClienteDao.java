package dao;

import dto.ClienteDTO;
import entidades.Cliente;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlClienteDao implements ClienteDao {
    private final Connection conexion;

    public MySqlClienteDao(ConeccionManager con) throws SQLException {
        this.conexion= con.getConnection();
    }
    @Override
    public void createTable() throws SQLException {
        conexion.setAutoCommit(false);
        String table = "CREATE TABLE cliente (" +
                "idCliente int  NOT NULL," +
                "nombre varchar(200)  NOT NULL," +
                "email varchar(200)  NOT NULL," +
                "CONSTRAINT Cliente_pk PRIMARY KEY (idCliente)" +
                ");";
        conexion.prepareStatement(table).execute();
        conexion.commit();

    }

    @Override
    public void readCSV(String path) {
        CSVParser parser;
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
            for (CSVRecord row : parser){
                int id = Integer.parseInt(row.get("idCliente"));
                String nombre = row.get("nombre");
                String email = row.get("email");
                Cliente cliente = new Cliente(id, nombre, email);
                this.addCliente(cliente);
            }
        }  catch (IOException|SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addCliente(Cliente cliente) throws SQLException {
        conexion.setAutoCommit(false);
        String insert="INSERT INTO cliente (idCliente,nombre, email) VALUES (?,?,?)";
        PreparedStatement ps=conexion.prepareStatement(insert);
        ps.setInt(1, cliente.getIdCliente());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getEmail());
        ps.execute();
        ps.close();
        conexion.commit();
    }

    @Override
    public List<ClienteDTO> getClientesOrdenadosPorFacturacion() throws SQLException  {
        List<ClienteDTO>resultado= new ArrayList<>();
        try{
            String select = "SELECT c.nombre, " +
                    "c.email, " +
                    "SUM(fp.cantidad * p.valor) AS total_facturacion " +
                    "FROM " +
                    "cliente c " +
                    "JOIN " +
                    " factura f USING (idCliente) " +
                    "JOIN " +
                    "facturaProducto fp USING(idFactura) " +
                    "JOIN " +
                    "producto p USING(idProducto) " +
                    "GROUP BY c.idCliente " +
                    "ORDER BY total_facturacion DESC ";
            PreparedStatement ps=conexion.prepareStatement(select);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                float total_facturacion = rs.getFloat("total_facturacion");
                ClienteDTO cliente = new ClienteDTO(nombre, email,total_facturacion);
                resultado.add(cliente);
            }
            ps.close();
            rs.close();
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
