package org.example;
import Dao.PersonaDao;
import Dao.PersonaDaoImp;
import Modelo.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PersonaMain {

    public static void main(String[] args) {
        // Configuración de la conexión
        String url = "jdbc:mysql://localhost:3307/dockerdb"; // Cambia la URL según tu configuración
        String usuario = "root";
        String pass = "password";

        try {
            Connection conn = DriverManager.getConnection(url, usuario, pass);

            // Crear una instancia del DAO de personas
            PersonaDao personaDao = new PersonaDaoImp(url,usuario,pass);

            // Resto del código (inserción, búsqueda, listado, etc.)
            Persona nuevaPersona = new Persona(10,"Pablo",51);

            personaDao.insertarPersona(nuevaPersona);

            int idBuscado=3;
            Persona personaBuscada=personaDao.getPersona(idBuscado);
            if(personaBuscada!=null){
                System.out.println(personaBuscada.getNombre()+personaBuscada.getEdad());
            }else{
                System.out.println("La persona con id " +idBuscado+" no existe" );
            }

            // Listar todas las personas (ejemplo)
            System.out.println("\nListado de personas:");
            for (Persona persona : personaDao.listarPersonas()) {
                System.out.println("ID: " + persona.getIdPersona() + ", Nombre: " + persona.getNombre()
                + ", Edad: " + persona.getEdad());
            }

            conn.close(); // No olvides cerrar la conexión al final
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
