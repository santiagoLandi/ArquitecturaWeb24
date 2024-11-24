package org.tudai.entregable3.dto;

import lombok.Getter;

@Getter
public class CarreraConCantidadInscriptosDTO {
    private String nombreCarrera;
    private long cantidadInscriptos;


    // Constructores
    public CarreraConCantidadInscriptosDTO() {}

    public CarreraConCantidadInscriptosDTO(String nombreCarrera, long cantidadInscriptos ) {
        this.nombreCarrera = nombreCarrera;
        this.cantidadInscriptos = cantidadInscriptos;
    }

    @Override
    public String toString() {
        return "CarreraConCantidadInscriptosDTO{" +
                "nombreCarrera='" + nombreCarrera + '\'' +
                ", cantidadInscriptos=" + cantidadInscriptos +
                '}';
    }
}
