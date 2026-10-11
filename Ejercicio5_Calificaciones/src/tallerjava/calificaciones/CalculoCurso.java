package tallerjava.calificaciones;

public class CalculoCurso {
    private EstudianteNotas[] alumnos;

    public CalculoCurso(int unidades) {
        alumnos = new EstudianteNotas[unidades];
    }

    public void agregar(int posicion, EstudianteNotas alumno) {
        alumnos[posicion] = alumno;
    }

    public double calcularPromedioOriginal() {
        double suma = 0;
        for (int i = 0; i < alumnos.length; i++) suma = suma + alumnos[i].getNotaOriginal();
        return suma / alumnos.length;
    }

    public double obtenerNotaMasAlta() {
        double mayor = 0;
        for (EstudianteNotas alumno : alumnos) if (alumno.getNotaOriginal() > mayor) mayor = alumno.getNotaOriginal();
        return mayor;
    }

    public void aplicarCurvaSegunMejorAlumno() {
        double puntos = 10 - obtenerNotaMasAlta();
        if (puntos <= 0) return;
        for (EstudianteNotas alumno : alumnos) {
            double original = alumno.getNotaOriginal();
            double porcentaje = original == 0 ? puntos * 10 : puntos * 100 / original;
            alumno.aplicarCurvaPorcentual(porcentaje);
        }
    }

    public int contarAprobadosGraciasCurva() {
        int total = 0;
        for (EstudianteNotas alumno : alumnos) if (!alumno.aproboOriginalmente() && alumno.estaAprobado()) total++;
        return total;
    }

    public double calcularMediaFinal() {
        double suma = 0;
        for (EstudianteNotas alumno : alumnos) suma += alumno.getNotaFinal();
        return suma / alumnos.length;
    }

    public double calcularDesviacionEstandar() {
        double media = calcularMediaFinal();
        double sumaCuadrados = 0;
        for (EstudianteNotas alumno : alumnos) {
            double diferencia = alumno.getNotaFinal() - media;
            sumaCuadrados += diferencia * diferencia;
        }
        return Math.sqrt(sumaCuadrados / alumnos.length);
    }

    public void mostrarResultados() {
        System.out.println("\n--- NOTAS FINALES ---");
        for (int i = 0; i < alumnos.length; i++) {
            String resultado = alumnos[i].estaAprobado() ? "Aprobó" : "Reprobó";
            System.out.printf("%s | original: %.2f | final: %.2f | %s%n",
                    alumnos[i].getNombre(), alumnos[i].getNotaOriginal(),
                    alumnos[i].getNotaFinal(), resultado);
        }
        System.out.printf("Media final: %.2f%n", calcularMediaFinal());
        System.out.printf("Desviación estándar: %.2f%n", calcularDesviacionEstandar());
        System.out.println("Pasaron de reprobado a aprobado por la curva: " + contarAprobadosGraciasCurva());
    }
}
