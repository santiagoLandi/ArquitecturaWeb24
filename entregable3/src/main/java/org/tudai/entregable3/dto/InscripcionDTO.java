package org.tudai.entregable3.dto;

import lombok.Getter;

@Getter
public class InscripcionDTO {
    private Integer anioInscripcion;
    private Integer anioEgreso;
    private boolean graduado;
    private String nombreEstudiante;
    private String nombreCarrera;

    public InscripcionDTO(Integer anioInscripcion, Integer anioEgreso, boolean graduado, String nombreEstudiante, String nombreCarrera) {
        this.anioInscripcion = anioInscripcion;
        this.anioEgreso = anioEgreso;
        this.graduado = graduado;
        this.nombreEstudiante = nombreEstudiante;
        this.nombreCarrera = nombreCarrera;
    }

    @Override
    public String toString() {
        return "InscripcionDTO{" +
                "anioInscripcion=" + anioInscripcion +
                ", anioEgreso=" + anioEgreso +
                ", graduado=" + graduado +
                ", nombreEstudiante='" + nombreEstudiante +
                ", nombreCarrera='" + nombreCarrera +
                '}';
    }
}
