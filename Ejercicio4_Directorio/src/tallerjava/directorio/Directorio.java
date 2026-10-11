package tallerjava.directorio;

import java.io.*;
import java.util.ArrayList;

public class Directorio {
    private ArrayList<FichaContacto> contactos = new ArrayList<>();
    private final String archivo = "agenda.txt";

    public boolean agregarContacto(FichaContacto c) {
        if (c.getNombre().trim().isEmpty() || !c.getTelefono().matches("[0-9]{9}")
                || !c.getEmail().contains("@") || !c.getEmail().contains(".")) return false;
        for (FichaContacto actual : contactos)
            if (actual.getNombre().equalsIgnoreCase(c.getNombre()) || actual.getTelefono().equals(c.getTelefono())) return false;
        contactos.add(c); guardarEnArchivo(); return true;
    }
    public ArrayList<FichaContacto> buscarContacto(String texto) {
        ArrayList<FichaContacto> encontrados = new ArrayList<>();
        for (FichaContacto c : contactos) if (c.getNombre().toLowerCase().contains(texto.toLowerCase())) encontrados.add(c);
        return encontrados;
    }
    public void mostrarTodos() {
        if (contactos.isEmpty()) { System.out.println("La agenda está vacía."); return; }
        for (FichaContacto c : contactos) c.mostrarInfo();
    }
    public void mostrarContactosPorGrupo(String etiquetaGrupo) {
        boolean encontrado = false;
        for (FichaContacto c : contactos) if (c.getGrupo().equalsIgnoreCase(etiquetaGrupo)) { c.mostrarInfo(); encontrado = true; }
        if (!encontrado) System.out.println("No hay contactos en ese etiquetaGrupo.");
    }
    public boolean eliminarContacto(String nombreCompleto) {
        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getNombre().equalsIgnoreCase(nombreCompleto)) {
                contactos.remove(i); guardarEnArchivo(); return true;
            }
        }
        return false;
    }
    public void guardarEnArchivo() {
        try (PrintWriter salida = new PrintWriter(new FileWriter(archivo))) {
            for (FichaContacto c : contactos) salida.println(c.getNombre() + "\t" + c.getTelefono() + "\t" + c.getEmail() + "\t" + c.getGrupo());
        } catch (IOException e) { System.out.println("No se pudo guardar la agenda."); }
    }
    public void cargarDesdeArchivo() {
        File f = new File(archivo);
        if (!f.exists()) return;
        try (BufferedReader entrada = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String[] datos = linea.split("\\t", -1);
                if (datos.length == 4) contactos.add(new FichaContacto(datos[0], datos[1], datos[2], datos[3]));
            }
        } catch (IOException e) { System.out.println("No se pudo cargar la agenda."); }
    }
}
