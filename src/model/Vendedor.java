package model;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Usuario {
    private final List<Producto> productos = new ArrayList<>();

    public Vendedor(String nombre, String correo, String contraseña) {
        super(nombre, correo, contraseña);
    }

    public List<Producto> getProductos() { return productos; }

    public void publicarProducto(Producto p) {
        if (p != null) productos.add(p);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Vendedor: " + nombre + " - productos: " + productos.size());
    }
}
