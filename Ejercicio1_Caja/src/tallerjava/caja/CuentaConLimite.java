package tallerjava.caja;

public class CuentaConLimite extends CuentaUsuario {
    private double sobregiro;
    public CuentaConLimite(String propietario, double saldoDisponible, String claveAcceso, double sobregiro) { super(propietario, saldoDisponible, claveAcceso); this.sobregiro = sobregiro; }
    @Override public boolean retirar(double unidades) {
        double saldoActual = consultarSaldo();
        if (unidades <= 0 || unidades > saldoActual + sobregiro) return false;
        saldoDisponible -= unidades;
        registrarMovimiento("Retiro: -$" + unidades + " (incluye sobregiro)");
        return true;
    }
}
