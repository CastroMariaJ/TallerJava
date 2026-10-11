package tallerjava.academia;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GrupoCurso {
    private ArrayList<AlumnoCurso> estudiantes = new ArrayList<>();
    public void agregarEstudiante(AlumnoCurso estudiante) { estudiantes.add(estudiante); }
    public AlumnoCurso buscarEstudiante(String nombreCompleto) {
        for (AlumnoCurso e : estudiantes) if (e.getNombre().equalsIgnoreCase(nombreCompleto)) return e;
        return null;
    }
    public boolean modificarNota(String nombreEstudiante, String nombreAsignatura, double nota) {
        AlumnoCurso e = buscarEstudiante(nombreEstudiante);
        return e != null && e.modificarNota(nombreAsignatura, nota);
    }
    public double calcularPromedioGeneral() {
        if (estudiantes.isEmpty()) return 0;
        double suma = 0; for (AlumnoCurso e : estudiantes) suma += e.calcularPromedio();
        return suma / estudiantes.size();
    }
    public int contarAprobados() { int n = 0; for (AlumnoCurso e : estudiantes) if (e.aprobo()) n++; return n; }
    public AlumnoCurso mejorEstudiante() {
        if (estudiantes.isEmpty()) return null;
        AlumnoCurso mejor = estudiantes.get(0);
        for (AlumnoCurso e : estudiantes) if (e.calcularPromedio() > mejor.calcularPromedio()) mejor = e;
        return mejor;
    }
    public AlumnoCurso peorEstudiante() {
        if (estudiantes.isEmpty()) return null;
        AlumnoCurso peor = estudiantes.get(0);
        for (AlumnoCurso e : estudiantes) if (e.calcularPromedio() < peor.calcularPromedio()) peor = e;
        return peor;
    }
    public void mostrarEstadisticas() {
        System.out.printf("Promedio general: %.2f%n", calcularPromedioGeneral());
        System.out.println("Aprobados: " + contarAprobados());
        System.out.println("Reprobados: " + (estudiantes.size() - contarAprobados()));
        if (!estudiantes.isEmpty()) {
            System.out.printf("Mejor: %s (%.2f)%n", mejorEstudiante().getNombre(), mejorEstudiante().calcularPromedio());
            System.out.printf("Menor: %s (%.2f)%n", peorEstudiante().getNombre(), peorEstudiante().calcularPromedio());
        }
    }
    public void guardarReporte(String nombreArchivo) {
        try (FileWriter archivo = new FileWriter(nombreArchivo)) {
            archivo.write("Reporte del curso\n");
            for (AlumnoCurso e : estudiantes) archivo.write(e.getNombre() + " | mediaCurso: " + e.calcularPromedio() + " | ausencias: " + e.getFaltas() + "\n");
            archivo.write("Promedio general: " + calcularPromedioGeneral() + "\nAprobados: " + contarAprobados() + "\nReprobados: " + (estudiantes.size() - contarAprobados()));
        } catch (IOException error) { System.out.println("No se pudo guardar el reporte."); }
    }
}
