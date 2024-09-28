package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEstudiante;

    @Column
    private String nombres;

    @Column
    private String apellido;

    @Column
    private Integer anioNacimiento;

    @Column
    private String genero;

    @Column
    private int dni;

    @Column
    private String ciudadResidencia;

    @Column
    private long libretaUniv;

    // Relación uno a muchos con la entidad Inscripcion
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // CascadeType.ALL significa que todas las operaciones de cascada se aplicarán a las entidades relacionadas
    private List<Inscripcion> inscripciones;

    // Constructores
    public Estudiante() {
        this.inscripciones = new ArrayList<Inscripcion>();
    }

    public Estudiante(String nombres, String apellido, Integer anioNacimiento, String genero, int dni, String ciudadResidencia, long libretaUniv) {
        this.nombres = nombres;
        this.apellido = apellido;
        this.anioNacimiento = anioNacimiento;
        this.genero = genero;
        this.dni = dni;
        this.ciudadResidencia = ciudadResidencia;
        this.libretaUniv = libretaUniv;
        this.inscripciones = new ArrayList<Inscripcion>();
    }

    // Getters y Setters
    public int getId() {
        return idEstudiante;
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
   public Integer getEdad(){
        return LocalDate.now().getYear()-this.anioNacimiento;
   }

    public void setAnioNacimiento(Integer anio) {
        this.anioNacimiento = anio;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDni() {
        return this.dni;
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

    public void setLibretaUniv(long lu) {
        this.libretaUniv = lu;
    }

    public List<Inscripcion> getInscripciones() {
        return new ArrayList<>(inscripciones);
    }

    public void addInscripcion(Inscripcion inscripcion) {
        if (!inscripciones.contains(inscripcion)) {
            inscripciones.add(inscripcion);
            inscripcion.setEstudiante(this); // Mantener la relación bidireccional
        }
    }

    public void removeInscripcion(Inscripcion inscripcion) {
        if (inscripciones.contains(inscripcion)) {
            inscripciones.remove(inscripcion);
            inscripcion.setEstudiante(null); // Mantener la relación bidireccional
        }
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + idEstudiante +
                ", nombres='" + nombres + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + this.getEdad() +
                ", genero='" + genero + '\'' +
                ", dni='" + dni + '\'' +
                ", ciudadResidencia='" + ciudadResidencia + '\'' +
                ", lu=" + libretaUniv +
                /*
                ", inscripciones=" + inscripciones +*/
                '}';
    }
}
