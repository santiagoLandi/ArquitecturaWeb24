package dto;

public class ClienteDTO {
    private String nombre;
    private String mail;
    private float montoFacturado;

    public ClienteDTO(String nombre, String mail, float montoFacturado) {
        this.nombre = nombre;
        this.mail = mail;
        this.montoFacturado = montoFacturado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public float getMontoFacturado() {
        return montoFacturado;
    }

    public void setMontoFacturado(float montoFacturado) {
        this.montoFacturado = montoFacturado;
    }

    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                ", e-mail='" + mail + '\'' +
                ", Monto facturado=" + montoFacturado;

    }
}
