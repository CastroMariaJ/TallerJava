package tallerjava.academia;

import java.util.ArrayList;

public class AlumnoCurso extends PersonaCurso {
    private ArrayList<MateriaCurso> materias = new ArrayList<>();
    private int ausencias;
    public AlumnoCurso(String nombreCompleto, int ausencias) { super(nombreCompleto); this.ausencias = ausencias; }
    public void agregarAsignatura(MateriaCurso asignatura) { materias.add(asignatura); }
    public ArrayList<MateriaCurso> getAsignaturas() { return materias; }
    public int getFaltas() { return ausencias; }
    public double calcularPromedio() {
        if (materias.isEmpty()) return 0;
        double suma = 0;
        for (MateriaCurso a : materias) suma += a.getNotaFinal();
        return suma / materias.size();
    }
    public boolean aprobo() { return calcularPromedio() >= 6 && ausencias < 10; }
    public boolean modificarNota(String nombreAsignatura, double nota) {
        for (MateriaCurso a : materias) if (a.getNombre().equalsIgnoreCase(nombreAsignatura)) { a.setNotaFinal(nota); return true; }
        return false;
    }
}
