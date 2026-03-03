package controller;

import model.Producto;
import service.ProductoService;

import java.util.List;

public class ProductoController {

    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    public List<Producto> obtenerProductos() {
        return productoService.getAllProductos();
    }

    public void agregarProducto(Producto producto) {
        productoService.addProducto(producto);
    }

    public Producto getProductoPorId(int id) {
        return productoService.getProductoById(id);
    }
}
