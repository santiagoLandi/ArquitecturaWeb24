package org.example.tp3springboot.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSocio;
    @Column
    private String tipoSocio;
    @OneToOne
    @MapsId
    private Persona persona;

    public Socio() {}

    public Socio(String tipoSocio, Persona persona) {
        this.tipoSocio = tipoSocio;
        this.persona = persona;
    }

}
