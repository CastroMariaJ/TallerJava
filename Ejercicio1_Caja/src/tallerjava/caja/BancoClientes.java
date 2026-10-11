package tallerjava.caja;

import java.util.ArrayList;

public class BancoClientes {
    private ArrayList<CuentaUsuario> cuentas = new ArrayList<>();
    public BancoClientes() {
        cuentas.add(new CuentaInteres("Estudiante", 500000, "1234"));
        cuentas.add(new CuentaConLimite("Invitado", 300000, "4321", 100000));
    }
    public CuentaUsuario buscarCuenta(String propietario) {
        for (CuentaUsuario cuenta : cuentas) if (cuenta.getTitular().equalsIgnoreCase(propietario)) return cuenta;
        return null;
    }
    public boolean validarPin(String propietario, String claveAcceso) {
        CuentaUsuario cuenta = buscarCuenta(propietario);
        return cuenta != null && cuenta.validarPin(claveAcceso);
    }
}
