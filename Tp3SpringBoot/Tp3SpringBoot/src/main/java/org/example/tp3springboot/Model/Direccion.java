package org.example.tp3springboot.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Direccion {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String calle;
    @Column
    private Integer numero;

    public Direccion() {}

    public Direccion(String calle, Integer numero) {
        this.calle = calle;
        this.numero = numero;
    }

}
