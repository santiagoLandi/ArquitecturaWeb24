package dtos;

import entities.Estudiante;

import javax.persistence.Column;

public class EstudianteDTO {
    private String nombres;
    private String apellido;
    private Integer anioNacimiento;
    private String genero;
    private int dni;
    private String ciudadResidencia;
    private long libretaUniv;

    public EstudianteDTO() {}

    public EstudianteDTO(String nombres, String apellido, Integer anioNacimiento, String genero, int dni, String ciudadResidencia, long libretaUniv) {
        this.nombres = nombres;
        this.apellido = apellido;
        this.anioNacimiento = anioNacimiento;
        this.genero = genero;
        this.dni = dni;
        this.ciudadResidencia = ciudadResidencia;
        this.libretaUniv = libretaUniv;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public long getLibretaUniv() {
        return libretaUniv;
    }

    public void setLibretaUniv(long libretaUniv) {
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
