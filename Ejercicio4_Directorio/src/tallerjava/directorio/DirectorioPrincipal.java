package tallerjava.directorio;

import java.util.Scanner;

public class DirectorioPrincipal {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ejecutar(entrada);
    }

    public static void ejecutar(Scanner entrada) {
        Directorio agenda = new Directorio();
        agenda.cargarDesdeArchivo();
        int eleccion = 0;

        while (eleccion != 6) {
            System.out.println("\n--- AGENDA ---");
            System.out.println("1. Registrar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Listar contactos");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Ver contactos por etiquetaGrupo");
            System.out.println("6. Salir");
            eleccion = leerEntero(entrada, "Elige una opción: ");

            if (eleccion == 1) {
                System.out.print("Nombre: ");
                String nombreCompleto = entrada.nextLine();
                System.out.print("Teléfono: ");
                String celular = entrada.nextLine();
                System.out.print("Email: ");
                String correo = entrada.nextLine();
                System.out.print("Grupo (Familia, Trabajo, Amigos...): ");
                String etiquetaGrupo = entrada.nextLine();
                if (agenda.agregarContacto(new FichaContacto(nombreCompleto, celular, correo, etiquetaGrupo))) {
                    System.out.println("FichaContacto guardado.");
                } else {
                    System.out.println("Datos no válidos, o el nombreCompleto/teléfono ya está registrado. Usa un teléfono de 9 dígitos.");
                }
            } else if (eleccion == 2) {
                System.out.print("Texto del nombreCompleto a buscar: ");
                java.util.ArrayList<FichaContacto> encontrados = agenda.buscarContacto(entrada.nextLine());
                if (encontrados.isEmpty()) System.out.println("No se encontraron contactos.");
                else for (FichaContacto contacto : encontrados) contacto.mostrarInfo();
            } else if (eleccion == 3) {
                agenda.mostrarTodos();
            } else if (eleccion == 4) {
                System.out.print("Nombre a eliminar: ");
                if (agenda.eliminarContacto(entrada.nextLine())) System.out.println("FichaContacto eliminado.");
                else System.out.println("No se encontró el contacto.");
            } else if (eleccion == 5) {
                System.out.print("Grupo a mostrar: ");
                agenda.mostrarContactosPorGrupo(entrada.nextLine());
            } else if (eleccion == 6) {
                System.out.println("Directorio cerrada.");
            } else {
                System.out.println("Opción no válida.");
            }
        }
        // El menú general comparte la entrada, por eso no cerramos System.in aquí.
    }

    private static int leerEntero(Scanner entrada, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException error) {
                System.out.println("Escribe un número entero.");
            }
        }
    }
}
