package tallerjava.comercio;

public class ArticuloElectronico extends Articulo {
    private int mesesGarantia;
    public ArticuloElectronico(String nombreCompleto, double valor, int existencias, int mesesGarantia) {
        super(nombreCompleto, valor, existencias, "Electrónica"); this.mesesGarantia = mesesGarantia;
    }
    public int getMesesGarantia() { return mesesGarantia; }
}
