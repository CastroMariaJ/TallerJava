package tallerjava.directorio;

public class FichaContacto {
    private String nombreCompleto;
    private String celular;
    private String correo;
    private String etiquetaGrupo;

    public FichaContacto(String nombreCompleto, String celular, String correo, String etiquetaGrupo) {
        this.nombreCompleto = nombreCompleto;
        this.celular = celular;
        this.correo = correo;
        this.etiquetaGrupo = etiquetaGrupo;
    }

    public String getNombre() { return nombreCompleto; }
    public String getTelefono() { return celular; }
    public String getEmail() { return correo; }
    public String getGrupo() { return etiquetaGrupo; }

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombreCompleto + " | Teléfono: " + celular + " | Email: " + correo + " | Grupo: " + etiquetaGrupo);
    }
}
