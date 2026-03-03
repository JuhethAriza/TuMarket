package controller;

import model.Producto;
import service.CarritoService;

import java.util.List;

public class CarritoController {

    private CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    public void agregarProducto(int idUsuario, Producto producto) {
        carritoService.addToCarrito(idUsuario, producto);
    }

    public List<Producto> obtenerCarrito(int idUsuario) {
        return carritoService.getCarrito(idUsuario);
    }

    public void limpiarCarrito(int idUsuario) {
        carritoService.clearCarrito(idUsuario);
    }
}
