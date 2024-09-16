package dto;

public class ProductoDTO {
    private String nombre;
    private float valor;
    private float totalVentas;

    public ProductoDTO(String nombre, float valor, float totalVentas) {
        this.nombre = nombre;
        this.valor = valor;
        this.totalVentas = totalVentas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public float getTotalVentas() {
        return totalVentas;
    }

    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", totalVentas=" + totalVentas;
    }
}
