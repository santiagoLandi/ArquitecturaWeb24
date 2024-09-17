package model;

import jakarta.persistence.*;

@Entity
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSocio;
    @Column
    private String tipo;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Persona persona;

    public Socio() {
    }

    public Socio(String tipo, Persona persona) {
        this.tipo = tipo;
        this.persona = persona;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public String getTipo() {
        return tipo;
    }

    public Persona getPersona() {
        return persona;
    }

    @Override
    public String toString() {
        return "tipo='" + tipo +
                ", persona=" + persona;
    }
}
