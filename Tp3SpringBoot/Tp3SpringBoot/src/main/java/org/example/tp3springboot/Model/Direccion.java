package org.example.tp3springboot.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String calle;
    @Column
    private Integer numero;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Persona>habitantes;

    public Direccion() {}

    public Direccion(String calle, Integer numero) {
        this.calle = calle;
        this.numero = numero;
        this.habitantes = new ArrayList<Persona>();
    }





}
