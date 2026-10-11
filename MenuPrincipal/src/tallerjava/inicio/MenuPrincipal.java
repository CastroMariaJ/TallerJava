package tallerjava.inicio;

import java.util.Scanner;
import tallerjava.caja.CajeroDigital;
import tallerjava.academia.ControlCurso;
import tallerjava.comercio.PuntoDeVenta;
import tallerjava.directorio.DirectorioPrincipal;
import tallerjava.calificaciones.EvaluadorCurso;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int eleccion = -1;

        while (eleccion != 0) {
            System.out.println("\n¿A qué ejercicio quieres ir?");
            System.out.println("1. Cajero automático");
            System.out.println("2. Registro académico");
            System.out.println("3. Tienda online");
            System.out.println("4. Directorio de contactos");
            System.out.println("5. Sistema de notas con curva");
            System.out.println("0. Salir");
            eleccion = leerOpcion(entrada);

            if (eleccion == 1) {
                CajeroDigital.ejecutar(entrada);
            } else if (eleccion == 2) {
                ControlCurso.ejecutar(entrada);
            } else if (eleccion == 3) {
                PuntoDeVenta.ejecutar(entrada);
            } else if (eleccion == 4) {
                DirectorioPrincipal.ejecutar(entrada);
            } else if (eleccion == 5) {
                EvaluadorCurso.ejecutar(entrada);
            } else if (eleccion == 0) {
                System.out.println("Taller finalizado.");
            } else {
                System.out.println("Opción no válida. Elige un número del 0 al 5.");
            }
        }

        // El menú es el último programa en usar la entrada.
        entrada.close();
    }

    private static int leerOpcion(Scanner entrada) {
        while (true) {
            System.out.print("Selecciona una opción (0 a 5): ");
            try {
                return Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException error) {
                System.out.println("Escribe un número entero.");
            }
        }
    }
}
