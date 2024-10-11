package org.tudai.entregable3.dto;

import lombok.Getter;

@Getter
public class EstudianteDTO {
    private String nombres;
    private String apellido;
    private Integer anioNacimiento;
    private String genero;
    private Long dni;
    private String ciudadResidencia;
    private Long libretaUniv;

    public EstudianteDTO() {}

    public EstudianteDTO(String nombres, String apellido, Integer anioNacimiento, String genero, Long dni, String ciudadResidencia, Long libretaUniv) {
        this.nombres = nombres;
        this.apellido = apellido;
        this.anioNacimiento = anioNacimiento;
        this.genero = genero;
        this.dni = dni;
        this.ciudadResidencia = ciudadResidencia;
        this.libretaUniv = libretaUniv;
    }


    @Override
    public String toString() {
        return  "nombres='" + nombres + '\'' +
                ", apellido='" + apellido + '\'' +
                ", anioNacimiento=" + anioNacimiento +
                ", genero='" + genero + '\'' +
                ", dni=" + dni +
                ", ciudadResidencia='" + ciudadResidencia + '\'' +
                ", libretaUniv=" + libretaUniv +
                '}';
    }
}
