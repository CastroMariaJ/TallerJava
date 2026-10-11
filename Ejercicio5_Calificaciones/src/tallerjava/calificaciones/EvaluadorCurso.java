package tallerjava.calificaciones;

import java.util.Scanner;
import java.util.InputMismatchException;

public class EvaluadorCurso {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ejecutar(entrada);
    }

    public static void ejecutar(Scanner entrada) {
        int unidades = leerEnteroPositivo(entrada, "¿Cuántos alumnos vas a registrar? ");
        CalculoCurso gestion = new CalculoCurso(unidades);

        for (int i = 0; i < unidades; i++) {
            System.out.println("\nAlumno " + (i + 1));
            System.out.print("Nombre: ");
            String nombreCompleto = entrada.nextLine();
            double parcial1 = leerNota(entrada, "Nota del parcial 1 (30%): ");
            double parcial2 = leerNota(entrada, "Nota del parcial 2 (30%): ");
            double examen = leerNota(entrada, "Nota del examen final (40%): ");
            gestion.agregar(i, new EstudianteNotas(nombreCompleto, parcial1, parcial2, examen));
        }

        double mediaCurso = gestion.calcularPromedioOriginal();
        System.out.printf("\nPromedio original del curso: %.2f%n", mediaCurso);
        if (mediaCurso < 6) {
            System.out.printf("El mediaCurso es menor que 6. Se aplicará una curva de %.2f puntos, calculada con la mejor nota.%n",
                    10 - gestion.obtenerNotaMasAlta());
            gestion.aplicarCurvaSegunMejorAlumno();
        } else {
            System.out.println("No es necesario aplicar curva.");
        }
        gestion.mostrarResultados();
        // El menú general comparte la entrada, por eso no cerramos System.in aquí.
    }

    private static int leerEnteroPositivo(Scanner entrada, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(entrada.nextLine());
                if (valor > 0) return valor;
            } catch (NumberFormatException error) {
                // Se informa el error con el mensaje de abajo.
            }
            System.out.println("Escribe un entero mayor que cero.");
        }
    }

    private static double leerNota(Scanner entrada, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double nota = entrada.nextDouble();
                entrada.nextLine();
                if (nota >= 0 && nota <= 10) return nota;
                System.out.println("La nota debe estar entre 0 y 10.");
            } catch (InputMismatchException error) {
                System.out.println("Escribe un número válido.");
                entrada.nextLine();
            }
        }
    }
}
