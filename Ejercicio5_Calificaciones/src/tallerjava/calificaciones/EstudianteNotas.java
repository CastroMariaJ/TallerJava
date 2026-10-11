package tallerjava.calificaciones;

public class EstudianteNotas extends PersonaNotas {
    private double evaluacionUno;
    private double evaluacionDos;
    private double evaluacionCierre;
    private double calificacionFinal;
    public EstudianteNotas(String nombreCompleto, double parcial1, double parcial2, double examen) {
        super(nombreCompleto); evaluacionUno = parcial1; evaluacionDos = parcial2; evaluacionCierre = examen;
        calificacionFinal = calcularNotaOriginal();
    }
    public double calcularNotaOriginal() { return evaluacionUno * .30 + evaluacionDos * .30 + evaluacionCierre * .40; }
    public void aplicarCurvaPorcentual(double porcentaje) {
        double original = calcularNotaOriginal();
        double aumento = original == 0 ? porcentaje / 10.0 : original * porcentaje / 100.0;
        calificacionFinal = Math.min(10, calificacionFinal + aumento);
    }
    public void aplicarCurvaFija(double puntos) { calificacionFinal = Math.min(10, calificacionFinal + puntos); }
    public boolean aproboOriginalmente() { return calcularNotaOriginal() >= 6; }
    public boolean estaAprobado() { return calificacionFinal >= 6; }
    public double getNotaOriginal() { return calcularNotaOriginal(); }
    public double getNotaFinal() { return calificacionFinal; }
}
