import java.util.Scanner;

class Contacto {
    private String nombre;
    private String telefono;
    private String email;

    public Contacto(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre + " | Teléfono: " + telefono + " | Email: " + email);
    }
}

class Agenda {
    private Contacto[] contactos;
    private int cantidad;

    public Agenda() {
        contactos = new Contacto[20];
        cantidad = 0;
    }

    public boolean agregarContacto(Contacto contacto) {
        if (cantidad == contactos.length) {
            return false;
        }

        for (int i = 0; i < cantidad; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(contacto.getNombre())
                    || contactos[i].getTelefono().equals(contacto.getTelefono())) {
                return false;
            }
        }

        contactos[cantidad] = contacto;
        cantidad++;
        return true;
    }

    public Contacto buscarContacto(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre)) {
                return contactos[i];
            }
        }
        return null;
    }

    public void mostrarTodos() {
        if (cantidad == 0) {
            System.out.println("La agenda está vacía.");
            return;
        }
        for (int i = 0; i < cantidad; i++) {
            contactos[i].mostrarInfo();
        }
    }

    public boolean eliminarContacto(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre)) {
                // Se corre el resto del arreglo para cerrar el espacio que quedó libre.
                for (int j = i; j < cantidad - 1; j++) {
                    contactos[j] = contactos[j + 1];
                }
                cantidad--;
                contactos[cantidad] = null;
                return true;
            }
        }
        return false;
    }
}

public class Ejercicio4AgendaContactos {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Agenda agenda = new Agenda();
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n--- AGENDA ---");
            System.out.println("1. Registrar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Listar contactos");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Salir");
            opcion = leerEntero(teclado, "Elige una opción: ");

            if (opcion == 1) {
                System.out.print("Nombre: ");
                String nombre = teclado.nextLine();
                System.out.print("Teléfono: ");
                String telefono = teclado.nextLine();
                System.out.print("Email: ");
                String email = teclado.nextLine();

                if (agenda.agregarContacto(new Contacto(nombre, telefono, email))) {
                    System.out.println("Contacto guardado.");
                } else {
                    System.out.println("No se pudo guardar. La agenda puede estar llena o el nombre/teléfono repetido.");
                }
            } else if (opcion == 2) {
                System.out.print("Nombre que deseas buscar: ");
                Contacto encontrado = agenda.buscarContacto(teclado.nextLine());
                if (encontrado == null) {
                    System.out.println("No se encontró ese contacto.");
                } else {
                    encontrado.mostrarInfo();
                }
            } else if (opcion == 3) {
                agenda.mostrarTodos();
            } else if (opcion == 4) {
                System.out.print("Nombre que deseas eliminar: ");
                if (agenda.eliminarContacto(teclado.nextLine())) {
                    System.out.println("Contacto eliminado.");
                } else {
                    System.out.println("No se encontró ese contacto.");
                }
            } else if (opcion == 5) {
                System.out.println("Agenda cerrada.");
            } else {
                System.out.println("Opción no válida.");
            }
        }

        teclado.close();
    }

    public static int leerEntero(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException error) {
                System.out.println("Escribe un número entero.");
            }
        }
    }
}
