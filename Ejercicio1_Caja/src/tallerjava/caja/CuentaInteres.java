package tallerjava.caja;

public class CuentaInteres extends CuentaUsuario {
    public CuentaInteres(String propietario, double saldoDisponible, String claveAcceso) { super(propietario, saldoDisponible, claveAcceso); }
    @Override public boolean depositar(double unidades) {
        if (!super.depositar(unidades)) return false;
        super.depositar(unidades * 0.01);
        return true;
    }
}
