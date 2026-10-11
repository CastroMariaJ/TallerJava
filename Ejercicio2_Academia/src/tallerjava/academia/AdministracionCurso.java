package tallerjava.academia;

public class AdministracionCurso {
    private GrupoCurso curso = new GrupoCurso();
    public void agregar(AlumnoCurso estudiante) { curso.agregarEstudiante(estudiante); }
    public void mostrarEstadisticas() { curso.mostrarEstadisticas(); }
    public void guardarReporte(String archivo) { curso.guardarReporte(archivo); }
    public GrupoCurso getCurso() { return curso; }
}
