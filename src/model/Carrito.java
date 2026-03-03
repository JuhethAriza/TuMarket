package model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private final List<Producto> items = new ArrayList<>();

    public void agregar(Producto p) {
        if (p != null) items.add(p);
    }

    public void remover(Producto p) {
        items.remove(p);
    }

    public List<Producto> getItems() {
        return items;
    }

    public double total() {
        return items.stream().mapToDouble(Producto::getPrecio).sum();
    }
}
