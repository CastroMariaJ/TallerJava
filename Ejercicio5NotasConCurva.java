import java.util.Scanner;

class Alumno {
    private String nombre;
    private double notaOriginal;
    private double notaFinal;

    public Alumno(String nombre, double notaOriginal) {
        this.nombre = nombre;
        this.notaOriginal = notaOriginal;
        notaFinal = notaOriginal;
    }

    public void aplicarCurva(int puntos) {
        notaFinal = notaFinal + puntos;
        if (notaFinal > 10) {
            notaFinal = 10;
        }
    }

    public boolean estaAprobado() {
        return notaFinal >= 6;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNotaOriginal() {
        return notaOriginal;
    }

    public double getNotaFinal() {
        return notaFinal;
    }
}

public class Ejercicio5NotasConCurva {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int cantidad = leerEnteroPositivo(teclado, "¿Cuántos alumnos vas a registrar? ");
        Alumno[] alumnos = new Alumno[cantidad];
        double sumaNotas = 0;

        for (int i = 0; i < alumnos.length; i++) {
            System.out.println("\nAlumno " + (i + 1));
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine();
            double nota = leerNota(teclado, "Nota original (0 a 10): ");
            alumnos[i] = new Alumno(nombre, nota);
            sumaNotas = sumaNotas + nota;
        }

        double promedio = sumaNotas / cantidad;
        System.out.printf("\nPromedio original del curso: %.2f%n", promedio);

        // Si el promedio está bajo, se suman dos puntos sin superar la nota máxima.
        if (promedio < 6) {
            System.out.println("El promedio es menor que 6. Se aplicarán 2 puntos de curva.");
            for (int i = 0; i < alumnos.length; i++) {
                alumnos[i].aplicarCurva(2);
            }
        } else {
            System.out.println("No es necesario aplicar curva.");
        }

        System.out.println("\n--- NOTAS FINALES ---");
        for (int i = 0; i < alumnos.length; i++) {
            String resultado = alumnos[i].estaAprobado() ? "Aprobó" : "Reprobó";
            System.out.printf("%s | original: %.2f | final: %.2f | %s%n",
                    alumnos[i].getNombre(), alumnos[i].getNotaOriginal(),
                    alumnos[i].getNotaFinal(), resultado);
        }

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
