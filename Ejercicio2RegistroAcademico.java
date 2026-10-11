import java.util.Scanner;

class Estudiante {
    private String nombre;
    private double[] notas;
    private double promedio;

    public Estudiante(String nombre, double[] notas) {
        this.nombre = nombre;
        this.notas = notas;
        calcularPromedio();
    }

    public void calcularPromedio() {
        double suma = 0;
        for (int i = 0; i < notas.length; i++) {
            suma = suma + notas[i];
        }
        promedio = suma / notas.length;
    }

    public boolean aprobo() {
        return promedio >= 6;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPromedio() {
        return promedio;
    }
}

public class Ejercicio2RegistroAcademico {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int cantidad = leerEnteroPositivo(teclado, "¿Cuántos estudiantes vas a registrar? ");
        Estudiante[] estudiantes = new Estudiante[cantidad];
        double sumaPromedios = 0;
        int aprobados = 0;
        int reprobados = 0;
        int indiceMayor = 0;
        int indiceMenor = 0;

        // Cada estudiante guarda sus tres notas y calcula su promedio al crearse.
        for (int i = 0; i < estudiantes.length; i++) {
            System.out.println("\nEstudiante " + (i + 1));
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine();
            double[] notas = new double[3];

            for (int j = 0; j < notas.length; j++) {
                notas[j] = leerNota(teclado, "Nota " + (j + 1) + " (0 a 10): ");
            }

            estudiantes[i] = new Estudiante(nombre, notas);
            sumaPromedios = sumaPromedios + estudiantes[i].getPromedio();

            if (estudiantes[i].aprobo()) {
                aprobados++;
            } else {
                reprobados++;
            }

            if (estudiantes[i].getPromedio() > estudiantes[indiceMayor].getPromedio()) {
                indiceMayor = i;
            }
            if (estudiantes[i].getPromedio() < estudiantes[indiceMenor].getPromedio()) {
                indiceMenor = i;
            }
        }

        System.out.println("\n--- RESULTADOS DEL GRUPO ---");
        System.out.printf("Promedio general: %.2f%n", sumaPromedios / cantidad);
        System.out.println("Aprobados: " + aprobados);
        System.out.println("Reprobados: " + reprobados);
        System.out.printf("Promedio más alto: %s (%.2f)%n",
                estudiantes[indiceMayor].getNombre(), estudiantes[indiceMayor].getPromedio());
        System.out.printf("Promedio más bajo: %s (%.2f)%n",
                estudiantes[indiceMenor].getNombre(), estudiantes[indiceMenor].getPromedio());

        teclado.close();
    }

    public static int leerEnteroPositivo(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int numero = Integer.parseInt(teclado.nextLine());
                if (numero > 0) {
                    return numero;
                }
                System.out.println("La cantidad debe ser mayor que cero.");
            } catch (NumberFormatException error) {
                System.out.println("Escribe un número entero.");
            }
        }
    }

    public static double leerNota(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double nota = Double.parseDouble(teclado.nextLine().replace(',', '.'));
                if (nota >= 0 && nota <= 10) {
                    return nota;
                }
                System.out.println("La nota debe estar entre 0 y 10.");
            } catch (NumberFormatException error) {
                System.out.println("Escribe una nota válida.");
            }
        }
    }
}
