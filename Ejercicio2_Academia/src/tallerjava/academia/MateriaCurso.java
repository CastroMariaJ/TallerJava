package tallerjava.academia;

public class MateriaCurso {
    private String nombreCompleto;
    private double calificacionFinal;
    public MateriaCurso(String nombreCompleto, double calificacionFinal) { this.nombreCompleto = nombreCompleto; this.calificacionFinal = calificacionFinal; }
    public String getNombre() { return nombreCompleto; }
    public double getNotaFinal() { return calificacionFinal; }
    public void setNotaFinal(double calificacionFinal) { this.calificacionFinal = calificacionFinal; }
}
