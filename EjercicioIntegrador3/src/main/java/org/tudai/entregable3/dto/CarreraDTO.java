package org.tudai.entregable3.dto;

import lombok.Getter;

@Getter
public class CarreraDTO {
    private String nombre;

    public CarreraDTO() {}

    public CarreraDTO(String nombre) {
        this.nombre = nombre;
    }



    @Override
    public String toString() {
        return "CarreraDTO{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
