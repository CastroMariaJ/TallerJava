package tallerjava.comercio;

import java.util.Scanner;
import java.util.ArrayList;

public class PuntoDeVenta {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ejecutar(entrada);
    }

    public static void ejecutar(Scanner entrada) {
        Almacen inventario = new Almacen();
        inventario.agregar(new Articulo("Cuaderno", 8000, 10, "Papelería"));
        inventario.agregar(new Articulo("Lapiz", 1500, 20, "Papelería"));
        inventario.agregar(new ArticuloComestible("Galletas", 2500, 12, "2027-05-01"));
        inventario.agregar(new ArticuloElectronico("Audífonos", 45000, 6, 12));
        System.out.print("Correo del cliente: ");
        Comprador cliente = new Comprador("Comprador", entrada.nextLine());
        BolsaCompra carrito = new BolsaCompra(inventario, cliente);
        int eleccion = -1;

        while (eleccion != 0) {
            System.out.println("\n--- TIENDA ---");
            System.out.println("Categorías: Papelería, Alimentos, Electrónica");
            System.out.print("Escribe una categoría o TODAS para ver todo: ");
            String seccion = entrada.nextLine();
            ArrayList<Integer> visibles = new ArrayList<>();
            for (int i = 0; i < inventario.getProductos().size(); i++) {
                Articulo p = inventario.obtener(i);
                if (seccion.equalsIgnoreCase("TODAS") || p.getCategoria().equalsIgnoreCase(seccion)) {
                    visibles.add(i);
                }
            }
            if (visibles.isEmpty()) System.out.println("No hay productos en esa categoría.");
            for (int i = 0; i < visibles.size(); i++) {
                Articulo p = inventario.obtener(visibles.get(i));
                System.out.printf("%d. %s (%s) - $%.2f, existencias: %d%n", i + 1,
                        p.getNombre(), p.getCategoria(), p.getPrecio(), p.getStock());
            }
            System.out.println("0. Terminar compra");
            eleccion = leerEntero(entrada, "Elige un producto: ");
            if (eleccion >= 1 && eleccion <= visibles.size()) {
                int unidades = leerEntero(entrada, "Cantidad: ");
                if (carrito.agregarProducto(visibles.get(eleccion - 1), unidades)) {
                    System.out.println("Articulo agregado.");
                } else {
                    System.out.println("Cantidad no válida o existencias insuficiente.");
                }
            } else if (eleccion != 0) {
                System.out.println("Opción no válida.");
            }
        }
        carrito.mostrarFactura();
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
