package service;

import model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    private static List<Producto> productos = new ArrayList<>();
  
    public void addProducto(Producto p) {
        productos.add(p);
    }

    public List<Producto> getAllProductos() {
        return productos;
    }
   
    public Producto getProductoById(int id) {
        return productos.stream()
                .filter(p -> {
                    try {
                        return Integer.parseInt(p.getId()) == id;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .findFirst()
                .orElse(null);
    }
}