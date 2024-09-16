package Dao;

import java.util.List;
import Modelo.Persona;

public interface PersonaDao {
    public List<Persona>listarPersonas();
    public Persona getPersona(int id);
    public void insertarPersona(Persona persona);
    public void borrarPersona(Persona persona);





}
