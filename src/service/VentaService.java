package service;

import model.Venta;
import java.util.*;

public class VentaService {
    private final List<Venta> ventas = new ArrayList<>();

    public void registrarVenta(Venta v) {
        if (v != null) ventas.add(v);
    }

    public List<Venta> obtenerVentas() {
        return new ArrayList<>(ventas);
    }
}
