package entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idInscripcion;

    @Column
    private Integer anioInscripcion;

    @Column
    private Integer anioEgreso; // Usar Integer para permitir valores null

    @Column
    private boolean graduado;

    // Relación muchos a uno con Carrera
    @ManyToOne
    @JoinColumn(name = "idCarrera", nullable = false)
    private Carrera carrera;

    // Relación muchos a uno con Estudiante
    @ManyToOne
    @JoinColumn(name = "idEstudiante", nullable = false)
    private Estudiante estudiante;

    // Constructores
    public Inscripcion() {

    }

    public Inscripcion( int anioInscripcion, Carrera carrera, Estudiante estudiante) {
        this.anioInscripcion = anioInscripcion;
        this.graduado = false;
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.anioEgreso=null;
    }


    public int getId() {
        return idInscripcion;
    }

    public Integer getAntiguedad() {
        if(isGraduado() == false){
            return LocalDate.now().getYear() - anioInscripcion;
        }else{
            return anioEgreso-anioInscripcion;
        }

    }

    public int getAnioInscripcion() {
        return anioInscripcion;
    }

    public void setAnioInscripcion(int anioInscripcion) {
        this.anioInscripcion = anioInscripcion;
    }


    public boolean isGraduado() {
        return graduado;
    }

    public void setGraduado(boolean graduado) {
        this.graduado = graduado;
    }
    public void setAnioEgreso(int anioEgreso) {
        this.anioEgreso = anioEgreso;
        this.setGraduado(true);
    }
    public Integer getAnioEgreso(){
        return this.anioEgreso;
    }
    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id=" + idInscripcion +
                ", antiguedad=" + anioEgreso +
                ", anioInscripcion=" + anioInscripcion +
                ", anioEgreso=" + anioEgreso +
                ", graduado=" + graduado +
                ", carrera=" + carrera +
                ", estudiante=" + estudiante +
                '}';
    }
}
