package services;

import dto.ClienteDTO;
import dao.ClienteDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteService {
    private ClienteDao clienteDao;

    public ClienteService(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public void addClientes(String path){
        this.clienteDao.readCSV(path);
    }

    public void createTable() throws SQLException {
        this.clienteDao.createTable();
    }

    public void getClientesOrdenadosXfacturacion() throws SQLException {
        ArrayList<ClienteDTO> clientes= (ArrayList<ClienteDTO>) this.clienteDao.getClientesOrdenadosPorFacturacion();
        System.out.println("Lista de clientes ordenados por facturacion");
        for(ClienteDTO cliente:clientes){
            System.out.println(cliente.toString());
        }
    }
}
