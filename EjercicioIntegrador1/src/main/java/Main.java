import dao.*;
import services.ClienteService;
import services.FacturaProductoService;
import services.FacturaService;
import services.ProductoService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Inicio el driver
        registerMySqlDriver();

        //Instancio la conexion MySql
        ConeccionManager con = new MySqlConeccionManager();

        //Instancio la clase DAO MySql de cada entidad
        ClienteDao clienteDao = new MySqlClienteDao(con);
        FacturaDao facturaDao = new MySqlFacturaDao(con);
        ProductoDao productoDao = new MySqlProductoDao(con);
        FacturaProductoDao facturaProductoDao = new MySqlFacturaProductoDao(con);

        //Instancio la clase service
        ClienteService clienteService = new ClienteService(clienteDao);
        FacturaService facturaService = new FacturaService(facturaDao);
        ProductoService productoService = new ProductoService(productoDao);
        FacturaProductoService facturaProductoService = new FacturaProductoService(facturaProductoDao);

        //Guardo las direcciones de los path
        String pathCliente = "src/main/resources/csv/clientes.csv";
        String pathFactura = "src/main/resources/csv/facturas.csv";
        String pathProducto = "src/main/resources/csv/productos.csv";
        String pathFacturaProducto = "src/main/resources/csv/facturas-productos.csv";

        /*
        //Creo las tablas en la base
        clienteService.createTable();
        facturaService.createTable();
        productoService.createTable();
        facturaProductoService.createTable();

        //Inserto datos en las tablas

        clienteService.addClientes(pathCliente);
        facturaService.addFacturas(pathFactura);
        productoService.addProductos(pathProducto);
        facturaProductoService.addFacturaProducto(pathFacturaProducto);
        */
        //Imprimo el resultado del producto con mayor facturacion
        productoService.productoMayorFacturacion();

        //Imprimo el listado de clientes ordenados por su facturacion
        clienteService.getClientesOrdenadosXfacturacion();


    }

    private static void registerMySqlDriver() {
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}