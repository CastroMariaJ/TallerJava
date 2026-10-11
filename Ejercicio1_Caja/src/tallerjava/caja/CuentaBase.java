package tallerjava.caja;

public abstract class CuentaBase implements OperacionesCuenta {
    protected String propietario;
    protected double saldoDisponible;
    private String claveAcceso;
    public CuentaBase(String propietario, double saldoInicial, String claveAcceso) { this.propietario = propietario; this.saldoDisponible = saldoInicial; this.claveAcceso = claveAcceso; }
    public String getTitular() { return propietario; }
    public boolean validarPin(String pinIngresado) { return claveAcceso.equals(pinIngresado); }
    @Override public double consultarSaldo() { return saldoDisponible; }
    public abstract void registrarMovimiento(String movimiento);
    public abstract void consultarMovimientos();
}
