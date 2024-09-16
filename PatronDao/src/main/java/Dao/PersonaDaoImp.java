package Dao;

import Modelo.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDaoImp implements PersonaDao {
    private final String url;
    private final String usuario;
    private final String pass;

    public PersonaDaoImp(String url, String usuario, String pass) {
        this.url = url;
        this.usuario = usuario;
        this.pass = pass;
    }
    @Override
    public List<Persona> listarPersonas() {
        List<Persona> personas = new ArrayList<>();

        String selectQuery = "SELECT * FROM persona";
        try (Connection conn = DriverManager.getConnection(url, usuario, pass);
             PreparedStatement ps = conn.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                personas.add(new Persona(id,nombre, edad));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return personas;
    }

    @Override
    public Persona getPersona(int id) {

        return null;
    }

    @Override
    public void insertarPersona(Persona persona) {


    }

    @Override
    public void borrarPersona(Persona persona) {

    }
    /*
    public void addPersona(Persona persona) throws SQLException {
        String insert = "INSERT INTO persona (nombre, edad) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setString(1, persona.getNombre());
            ps.setInt(2, persona.getEdad());
            ps.executeUpdate();
        }
    }

     */
}
