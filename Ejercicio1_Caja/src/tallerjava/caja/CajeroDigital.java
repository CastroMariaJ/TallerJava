package tallerjava.caja;

import java.util.Scanner;

public class CajeroDigital {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ejecutar(entrada);
    }

    public static void ejecutar(Scanner entrada) {
        BancoClientes banco = new BancoClientes();
        System.out.println("Clientes de prueba: Estudiante (PIN 1234), Invitado (PIN 4321)");
        System.out.print("Titular: ");
        String propietario = entrada.nextLine();
        CuentaUsuario cuenta = banco.buscarCuenta(propietario);
        boolean correcto = false;
        for (int i = 1; i <= 3 && cuenta != null; i++) {
            System.out.print("PIN: ");
            if (banco.validarPin(propietario, entrada.nextLine())) { correcto = true; break; }
            System.out.println("PIN incorrecto (" + i + "/3).");
        }
        if (!correcto) { System.out.println("CuentaBase no encontrada o bloqueada."); return; }
        ejecutarMenu(entrada, cuenta, banco);
        // El menú general comparte la entrada, por eso no cerramos System.in aquí.
    }

    private static void ejecutarMenu(Scanner entrada, CuentaUsuario cuenta, BancoClientes banco) {
        int eleccion = 0;
        while (eleccion != 6) {
            System.out.println("\n--- CAJERO ---");
            System.out.println("1. Consultar saldoDisponible  2. Retirar  3. Depositar  4. Transferir  5. Movimientos  6. Salir");
            eleccion = leerEntero(entrada, "Elige una opción: ");

            if (eleccion == 1) {
                System.out.printf("Saldo: $%.2f%n", cuenta.consultarSaldo());
            } else if (eleccion == 2) {
                double unidades = leerDecimal(entrada, "Cantidad a retirar: ");
                if (cuenta.retirar(unidades)) {
                    System.out.println("Retiro realizado.");
                } else {
                    System.out.println("Cantidad no válida o saldoDisponible insuficiente.");
                }
            } else if (eleccion == 3) {
                double unidades = leerDecimal(entrada, "Cantidad a depositar: ");
                if (cuenta.depositar(unidades)) {
                    System.out.println("Depósito realizado.");
                } else {
                    System.out.println("La unidades debe ser mayor que cero.");
                }
            } else if (eleccion == 4) {
                System.out.print("Titular destino: ");
                CuentaUsuario destino = banco.buscarCuenta(entrada.nextLine());
                double monto = leerDecimal(entrada, "Monto: ");
                if (cuenta.transferir(destino, monto)) System.out.println("Transferencia realizada.");
                else System.out.println("No se pudo transferir. Verifica destino y saldoDisponible.");
            } else if (eleccion == 5) {
                cuenta.consultarMovimientos();
            } else if (eleccion == 6) {
                System.out.println("Gracias por usar el cajero.");
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

    private static int leerEntero(Scanner entrada, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException error) {
                System.out.println("Escribe un número entero.");
            }
        }
    }

    private static double leerDecimal(Scanner entrada, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(entrada.nextLine().replace(',', '.'));
            } catch (NumberFormatException error) {
                System.out.println("Escribe una unidades válida.");
            }
        }
    }

}
