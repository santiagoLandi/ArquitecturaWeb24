package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDireccion;
    @Column
    private String ciudad;
    @Column
    private String calle;
    @OneToMany(mappedBy = "direccion", fetch = FetchType.LAZY)
    private List<Persona> personasHabitantes;

    public Direccion() {
        super();
    }

    public Direccion(String calle, String ciudad) {
        super();
        this.calle = calle;
        this.ciudad = ciudad;
        this.personasHabitantes = new ArrayList<Persona>();
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public List<Persona> getPersonasHabitantes() {
        return personasHabitantes;
    }

    public void addPersonasHabitantes(Persona persona) {
        if (this.personasHabitantes == null) {
            if (persona != null) {
                personasHabitantes.add(persona);
            }
        }
    }

    @Override
    public String toString() {
        return ", ciudad= " + ciudad +
                ", calle= " + calle + " / ";
    }
}
