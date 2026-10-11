package tallerjava.caja;

// Esta interfaz describe las operaciones básicas que puede hacer una cuenta.
public interface OperacionesCuenta {
    boolean depositar(double unidades);
    boolean retirar(double unidades);
    double consultarSaldo();
}
