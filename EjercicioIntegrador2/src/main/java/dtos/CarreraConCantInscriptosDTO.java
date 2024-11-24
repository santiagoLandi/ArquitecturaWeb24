package dtos;

import java.io.Serializable;

public class CarreraConCantInscriptosDTO implements Serializable {
    private String nombreCarrera;
    private long cantidadInscriptos;



    // Constructores
    public CarreraConCantInscriptosDTO() {}

    public CarreraConCantInscriptosDTO(String nombreCarrera, long cantidadInscriptos ) {
        this.nombreCarrera = nombreCarrera;
        this.cantidadInscriptos = cantidadInscriptos;
    }

    // Getters y Setters
    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public long getCantidadInscriptos() {
        return cantidadInscriptos;
    }

    public void setCantidadInscriptos(long cantidadInscriptos) {
        this.cantidadInscriptos = cantidadInscriptos;
    }


    @Override
    public String toString() {
        return "nombreCarrera='" + nombreCarrera + '\'' +
                ", cantidadInscriptos=" + cantidadInscriptos +
                '}';
    }


}
