package services;

import dao.ProductoDao;
import dto.ProductoDTO;
import entidades.Producto;

import java.sql.SQLException;

public class ProductoService {
    private ProductoDao productoDao;

    public ProductoService(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    public void addProductos(String path){
        this.productoDao.readCSV(path);
    }

    public void createTable() throws SQLException {
        this.productoDao.createTable();
    }
    public void productoMayorFacturacion() throws SQLException {
        ProductoDTO producto = this.productoDao.obtenerProductoConMasRecaudacion();
        System.out.println("El producto con mayor facturacion es: ");
        System.out.println(producto.toString());
    }
}
