package org.tudai.entregable3.dto;

public class ReporteCarreraDTO {

    private String nombreCarrera;
    private Integer anio;
    private Integer cantInscriptos;
    private Integer cantEgresados;

    public ReporteCarreraDTO(String nombreCarrera, Integer anio, Integer cantInscriptos, Integer cantEgresados ) {
        this.nombreCarrera = nombreCarrera;
        this.anio = anio;
        this.cantInscriptos = cantInscriptos;
        this.cantEgresados = cantEgresados;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getCantInscriptos() {
        return cantInscriptos;
    }

    public void setCantInscriptos(Integer cantInscriptos) {
        this.cantInscriptos = cantInscriptos;
    }

    public Integer getCantEgresados() {
        return cantEgresados;
    }

    public void setCantEgresados(Integer cantEgresados) {
        this.cantEgresados = cantEgresados;
    }

    @Override
    public String toString() {
        return "ReporteCarreraDTO{" +
                "nombreCarrera='" + nombreCarrera + '\'' +
                ", anio=" + anio +
                ", cantInscriptos=" + cantInscriptos +
                ", cantEgresados=" + cantEgresados +
                '}';
    }
}
