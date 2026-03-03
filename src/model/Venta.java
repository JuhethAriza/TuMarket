package model;

import java.util.List;

public class Venta {
    private String id;
    private Comprador comprador;
    private List<Producto> productos;
    private double total;

    public Venta(String id, Comprador comprador, List<Producto> productos, double total) {
        this.id = id;
        this.comprador = comprador;
        this.productos = productos;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public double getTotal() {
        return total;
    }
}
