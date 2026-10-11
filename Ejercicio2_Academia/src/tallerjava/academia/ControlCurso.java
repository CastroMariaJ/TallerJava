package tallerjava.academia;

import java.util.Scanner;

public class ControlCurso {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ejecutar(entrada);
    }

    public static void ejecutar(Scanner entrada) {
        int unidades = leerEnteroPositivo(entrada, "¿Cuántos estudiantes vas a registrar? ");
        AdministracionCurso gestion = new AdministracionCurso();

        for (int i = 0; i < unidades; i++) {
            System.out.println("\nEstudiante " + (i + 1));
            System.out.print("Nombre: ");
            String nombreCompleto = entrada.nextLine();
            int ausencias = leerEnteroPositivoOZero(entrada, "Número de ausencias: ");
            int cantidadAsignaturas = leerEnteroPositivo(entrada, "¿Cuántas materias? ");
            AlumnoCurso estudiante = new AlumnoCurso(nombreCompleto, ausencias);
            for (int j = 0; j < cantidadAsignaturas; j++) {
                System.out.print("Nombre de asignatura " + (j + 1) + ": ");
                String asignatura = entrada.nextLine();
                estudiante.agregarAsignatura(new MateriaCurso(asignatura, leerNota(entrada, "Nota (0 a 10): ")));
            }
            gestion.agregar(estudiante);
        }

        System.out.println("\n--- ESTADÍSTICAS DEL GRUPO ---");
        gestion.mostrarEstadisticas();
        System.out.print("¿Guardar reporte? (s/n): ");
        if (entrada.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Nombre del archivo (por ejemplo reporte.txt): ");
            gestion.guardarReporte(entrada.nextLine());
            System.out.println("Reporte guardado.");
        }
        // El menú general comparte la entrada, por eso no cerramos System.in aquí.
    }

    private static int leerEnteroPositivo(Scanner entrada, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(entrada.nextLine());
                if (valor > 0) return valor;
            } catch (NumberFormatException error) {
                // Se muestra el mismo mensaje de validación de abajo.
            }
            System.out.println("Escribe un entero mayor que cero.");
        }
    }

    private static double leerNota(Scanner entrada, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double nota = Double.parseDouble(entrada.nextLine().replace(',', '.'));
                if (nota >= 0 && nota <= 10) return nota;
            } catch (NumberFormatException error) {
                // Se muestra el mismo mensaje de validación de abajo.
            }
            System.out.println("La nota debe ser un número entre 0 y 10.");
        }
    }

    private static int leerEnteroPositivoOZero(Scanner entrada, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try { int valor = Integer.parseInt(entrada.nextLine()); if (valor >= 0) return valor; }
            catch (NumberFormatException error) { }
            System.out.println("Escribe un entero igual o mayor que cero.");
        }
    }
}
