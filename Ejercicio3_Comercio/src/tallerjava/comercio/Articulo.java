package tallerjava.comercio;

public class Articulo implements ArticuloComercial {
    private String nombreCompleto;
    private double valor;
    private int existencias;
    private String seccion;

    public Articulo(String nombreCompleto, double valor, int existencias) {
        this(nombreCompleto, valor, existencias, "General");
    }

    public Articulo(String nombreCompleto, double valor, int existencias, String seccion) {
        this.nombreCompleto = nombreCompleto;
        this.valor = valor;
        this.existencias = existencias;
        this.seccion = seccion;
    }

    @Override
    public String getNombre() { return nombreCompleto; }

    @Override
    public double getPrecio() { return valor; }

    public int getStock() { return existencias; }
    public String getCategoria() { return seccion; }

    public boolean hayStock(int unidades) {
        return unidades > 0 && unidades <= existencias;
    }

    public boolean disminuirStock(int unidades) {
        if (!hayStock(unidades)) return false;
        existencias = existencias - unidades;
        return true;
    }
}
