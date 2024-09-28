package dtos;

public class ReporteCarreraDTO {
    private String nombreCarrera;
    private Integer anioInscripcion;
    private Integer anioGraduacion;
    private Long cantidadInscriptos;
    private Long cantidadGraduados;

    public ReporteCarreraDTO() {}
    public ReporteCarreraDTO(String nombreCarrera, Integer anioInscripcion, Integer anioGraduacion, Long cantidadInscriptos, Long cantidadGraduados) {
        this.nombreCarrera = nombreCarrera;
        this.anioInscripcion = anioInscripcion;
        this.anioGraduacion = anioGraduacion;
        this.cantidadInscriptos = cantidadInscriptos;
        this.cantidadGraduados = cantidadGraduados;
    }

    @Override
    public String toString() {
        return "nombreCarrera='" + nombreCarrera + '\'' +
                ", anioInscripcion=" + anioInscripcion +
                ", anioGraduacion=" + anioGraduacion +
                ", cantidadInscriptos=" + cantidadInscriptos +
                ", cantidadGraduados=" + cantidadGraduados +
                '}';
    }
}
