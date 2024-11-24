package dtos;

public class ReporteCarreraDTO {
    private String nombreCarrera;
    private Integer anio;
    private long cantidadInscriptos;
    private long cantidadGraduados;

    public ReporteCarreraDTO() {}
    public ReporteCarreraDTO(String nombreCarrera, Integer anio, long cantidadInscriptos, long cantidadGraduados) {
        this.nombreCarrera = nombreCarrera;
        this.anio=anio;
        this.cantidadInscriptos = cantidadInscriptos;
        this.cantidadGraduados = cantidadGraduados;
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

    public long getCantidadInscriptos() {
        return cantidadInscriptos;
    }

    public void setCantidadInscriptos(Long cantidadInscriptos) {
        this.cantidadInscriptos = cantidadInscriptos;
    }

    public long getCantidadGraduados() {
        return cantidadGraduados;
    }

    public void setCantidadGraduados(Long cantidadGraduados) {
        this.cantidadGraduados = cantidadGraduados;
    }

    @Override
    public String toString() {
        return "nombreCarrera='" + nombreCarrera + '\'' +
                ", anioInscripcion=" + anio +
                ", cantidadInscriptos=" + cantidadInscriptos +
                ", cantidadGraduados=" + cantidadGraduados +
                '}';
    }
}
