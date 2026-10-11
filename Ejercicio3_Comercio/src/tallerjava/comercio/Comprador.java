package tallerjava.comercio;

public class Comprador {
    private String nombreCompleto;
    private String correo;
    public Comprador(String nombreCompleto, String correo) { this.nombreCompleto = nombreCompleto; this.correo = correo; }
    public String getNombre() { return nombreCompleto; }
    public String getEmail() { return correo; }
}
