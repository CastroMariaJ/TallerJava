import java.util.Scanner;

class CuentaBancaria {
    private String titular;
    private double saldo;
    private String pin;

    public CuentaBancaria(String titular, double saldo, String pin) {
        this.titular = titular;
        this.saldo = saldo;
        this.pin = pin;
    }

    public boolean validarPin(String pinIngresado) {
        return pin.equals(pinIngresado);
    }

    public void consultarSaldo() {
        System.out.printf("Saldo actual: $%.2f%n", saldo);
    }

    public boolean depositar(double cantidad) {
        if (cantidad <= 0) {
            return false;
        }
        saldo = saldo + cantidad;
        return true;
    }

    public boolean retirar(double cantidad) {
        if (cantidad <= 0 || cantidad > saldo) {
            return false;
        }
        saldo = saldo - cantidad;
        return true;
    }
}

public class Ejercicio1Cajero {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        CuentaBancaria cuenta = new CuentaBancaria("Estudiante", 500000, "1234");
        boolean ingresoCorrecto = false;

        // Se permiten como máximo tres intentos para ingresar el PIN.
        for (int intento = 1; intento <= 3; intento++) {
            System.out.print("Ingresa tu PIN: ");
            String pinIngresado = teclado.nextLine();

            if (cuenta.validarPin(pinIngresado)) {
                ingresoCorrecto = true;
                break;
            } else {
                System.out.println("PIN incorrecto. Intento " + intento + " de 3.");
            }
        }

        if (!ingresoCorrecto) {
            System.out.println("Cuenta bloqueada por seguridad.");
        } else {
            int opcion = 0;

            // El menú se repite hasta que la persona elija salir.
            while (opcion != 4) {
                System.out.println("\n--- CAJERO ---");
                System.out.println("1. Consultar saldo");
                System.out.println("2. Retirar dinero");
                System.out.println("3. Depositar dinero");
                System.out.println("4. Salir");
                opcion = leerEntero(teclado, "Elige una opción: ");

                if (opcion == 1) {
                    cuenta.consultarSaldo();
                } else if (opcion == 2) {
                    double cantidad = leerDecimal(teclado, "¿Cuánto deseas retirar? ");
                    if (cuenta.retirar(cantidad)) {
                        System.out.println("Retiro realizado.");
                    } else {
                        System.out.println("Cantidad no válida o saldo insuficiente.");
                    }
                } else if (opcion == 3) {
                    double cantidad = leerDecimal(teclado, "¿Cuánto deseas depositar? ");
                    if (cuenta.depositar(cantidad)) {
                        System.out.println("Depósito realizado.");
                    } else {
                        System.out.println("La cantidad debe ser mayor que cero.");
                    }
                } else if (opcion == 4) {
                    System.out.println("Gracias por usar el cajero.");
                } else {
                    System.out.println("Opción no válida.");
                }
            }
        }

        teclado.close();
    }

    // Estos dos métodos vuelven a preguntar si se escribe algo que no sea un número.
    public static int leerEntero(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException error) {
                System.out.println("Escribe un número entero.");
            }
        }
    }

    public static double leerDecimal(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(teclado.nextLine().replace(',', '.'));
            } catch (NumberFormatException error) {
                System.out.println("Escribe una cantidad válida.");
            }
        }
    }
}
