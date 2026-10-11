package tallerjava.comercio;

public class ArticuloComestible extends Articulo {
    private String fechaCaducidad;
    public ArticuloComestible(String nombreCompleto, double valor, int existencias, String fechaCaducidad) {
        super(nombreCompleto, valor, existencias, "Alimentos"); this.fechaCaducidad = fechaCaducidad;
    }
    public String getFechaCaducidad() { return fechaCaducidad; }
}
