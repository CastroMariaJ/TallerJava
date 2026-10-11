import java.util.Scanner;

class Producto {
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public boolean hayStock(int cantidad) {
        return cantidad > 0 && cantidad <= stock;
    }

    public boolean disminuirStock(int cantidad) {
        if (!hayStock(cantidad)) {
            return false;
        }
        stock = stock - cantidad;
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }
}

class Carrito {
    private Producto[] catalogo;
    private int[] cantidades;

    public Carrito(Producto[] catalogo) {
        this.catalogo = catalogo;
        cantidades = new int[catalogo.length];
    }

    public boolean agregarProducto(int posicion, int cantidad) {
        Producto producto = catalogo[posicion];
        if (!producto.hayStock(cantidad)) {
            return false;
        }

        producto.disminuirStock(cantidad);
        cantidades[posicion] = cantidades[posicion] + cantidad;
        return true;
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < catalogo.length; i++) {
            total = total + catalogo[i].getPrecio() * cantidades[i];
        }
        return total;
    }

    public void mostrarFactura() {
        System.out.println("\n--- FACTURA ---");
        boolean hayCompras = false;
        for (int i = 0; i < catalogo.length; i++) {
            if (cantidades[i] > 0) {
                hayCompras = true;
                double subtotal = catalogo[i].getPrecio() * cantidades[i];
                System.out.printf("%s x%d: $%.2f%n", catalogo[i].getNombre(), cantidades[i], subtotal);
            }
        }
        if (!hayCompras) {
            System.out.println("No agregaste productos.");
        }
        System.out.printf("Total a pagar: $%.2f%n", calcularTotal());
    }
}

public class Ejercicio3TiendaOnline {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Producto[] catalogo = {
            new Producto("Cuaderno", 8000, 10),
            new Producto("Lapiz", 1500, 20),
            new Producto("Mochila", 65000, 5)
        };
        Carrito carrito = new Carrito(catalogo);
        int opcion = 0;

        // La compra continúa hasta que se elija terminar.
        while (opcion != catalogo.length + 1) {
            System.out.println("\n--- TIENDA ---");
            for (int i = 0; i < catalogo.length; i++) {
                System.out.printf("%d. %s - $%.2f (stock: %d)%n", i + 1,
                        catalogo[i].getNombre(), catalogo[i].getPrecio(), catalogo[i].getStock());
            }
            System.out.println((catalogo.length + 1) + ". Terminar compra");
            opcion = leerEntero(teclado, "Elige un producto o termina la compra: ");

            if (opcion >= 1 && opcion <= catalogo.length) {
                int cantidad = leerEntero(teclado, "¿Cuántas unidades deseas? ");
                if (carrito.agregarProducto(opcion - 1, cantidad)) {
                    System.out.println("Producto agregado al carrito.");
                } else {
                    System.out.println("Cantidad no válida o no hay suficiente stock.");
                }
            } else if (opcion != catalogo.length + 1) {
                System.out.println("Opción no válida.");
            }
        }

        carrito.mostrarFactura();
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
