package service;

import model.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CarritoService {

    // Un carrito por usuario
    private static HashMap<Integer, List<Producto>> carritos = new HashMap<>();

    public void addToCarrito(int idUsuario, Producto producto) {
        carritos.putIfAbsent(idUsuario, new ArrayList<>());
        carritos.get(idUsuario).add(producto);
    }

    public List<Producto> getCarrito(int idUsuario) {
        carritos.putIfAbsent(idUsuario, new ArrayList<>());
        return carritos.get(idUsuario);
    }

    public void clearCarrito(int idUsuario) {
        carritos.put(idUsuario, new ArrayList<>());
    }
}
