package dtos;

import java.io.Serializable;

public class Reporte implements Serializable {
    private String nombreCarrera;
    private Integer anioInscripcion;
    private Integer anioEgreso;
    private long cantidadInscriptos;
    private long cantidadEgresados;

    // Constructores
    public Reporte() {}

    public Reporte(String nombreCarrera, Integer anioInscripcion, Integer anioEgreso, long cantidadInscriptos, long cantidadEgresados) {
        this.nombreCarrera = nombreCarrera;
        this.anioInscripcion = anioInscripcion;
        this.anioEgreso = anioEgreso;
        this.cantidadInscriptos = cantidadInscriptos;
        this.cantidadEgresados = cantidadEgresados;
    }

    // Getters y Setters
    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public Integer getAnioInscripcion() {
        return anioInscripcion;
    }

    public void setAnioInscripcion(int anioInscripcion) {
        this.anioInscripcion = anioInscripcion;
    }

    public Integer getAnioEgreso() {
        return anioEgreso;
    }

    public void setAnioEgreso(int anioEgreso) {
        this.anioEgreso = anioEgreso;
    }

    public long getCantidadInscriptos() {
        return cantidadInscriptos;
    }

    public void setCantidadInscriptos(long cantidadInscriptos) {
        this.cantidadInscriptos = cantidadInscriptos;
    }

    public long getCantidadEgresados() {
        return cantidadEgresados;
    }

    public void setCantidadEgresados(long cantidadEgresados) {
        this.cantidadEgresados = cantidadEgresados;
    }

    @Override
    public String toString() {
        return "ReporteCarreraDTO{" +
                "nombreCarrera='" + nombreCarrera + '\'' +
                ", anioInscripcion=" + anioInscripcion +
                ", anioEgreso=" + anioEgreso +
                ", cantidadInscriptos=" + cantidadInscriptos +
                ", cantidadEgresados=" + cantidadEgresados +
                '}';
    }
}
