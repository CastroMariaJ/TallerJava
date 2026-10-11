package tallerjava.caja;

import java.util.ArrayList;

public class CuentaUsuario extends CuentaBase {
    private ArrayList<String> movimientos = new ArrayList<>();
    public CuentaUsuario(String propietario, double saldoInicial, String claveAcceso) { super(propietario, saldoInicial, claveAcceso); }
    @Override public boolean depositar(double unidades) {
        if (unidades <= 0) return false;
        saldoDisponible += unidades; registrarMovimiento("Depósito: +$" + unidades); return true;
    }
    @Override public boolean retirar(double unidades) {
        if (unidades <= 0 || unidades > saldoDisponible) return false;
        saldoDisponible -= unidades; registrarMovimiento("Retiro: -$" + unidades); return true;
    }
    public boolean transferir(CuentaUsuario destino, double monto) {
        if (destino == null || destino == this || monto <= 0 || monto > saldoDisponible) return false;
        saldoDisponible -= monto; destino.recibirTransferencia(monto, propietario);
        registrarMovimiento("Transferencia enviada: -$" + monto + " a " + destino.getTitular()); return true;
    }
    private void recibirTransferencia(double monto, String origen) {
        saldoDisponible += monto; registrarMovimiento("Transferencia recibida: +$" + monto + " de " + origen);
    }
    @Override public void registrarMovimiento(String movimiento) {
        movimientos.add(movimiento); if (movimientos.size() > 5) movimientos.remove(0);
    }
    @Override public void consultarMovimientos() {
        if (movimientos.isEmpty()) System.out.println("Todavía no hay movimientos.");
        for (String movimiento : movimientos) System.out.println(movimiento);
    }
}
