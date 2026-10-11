package tallerjava.comercio;

import java.util.ArrayList;

public class Almacen {
    private ArrayList<Articulo> productos = new ArrayList<>();
    public void agregar(Articulo producto) { productos.add(producto); }
    public ArrayList<Articulo> getProductos() { return productos; }
    public Articulo obtener(int posicion) {
        if (posicion < 0 || posicion >= productos.size()) return null;
        return productos.get(posicion);
    }
    public boolean vender(int posicion, int unidades) {
        Articulo p = obtener(posicion);
        return p != null && p.disminuirStock(unidades);
    }
}
