package view;

import controller.UsuarioController;
import controller.ProductoController;
import controller.CarritoController;
import controller.VentaController;

import service.UsuarioService;
import service.ProductoService;
import service.CarritoService;
import service.VentaService;

import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioService();
        ProductoService productoService = new ProductoService();
        CarritoService carritoService = new CarritoService();
        VentaService ventaService = new VentaService();

        UsuarioController usuarioController = new UsuarioController(usuarioService);
        ProductoController productoController = new ProductoController(productoService);
        CarritoController carritoController = new CarritoController(carritoService);
        VentaController ventaController = new VentaController(ventaService);

        // invokeLater ejecuta el código para evitar problemas de concurrencia.
        SwingUtilities.invokeLater(() -> {
            LoginFrame login = new LoginFrame(usuarioController, productoController, carritoController, ventaController);
            login.setVisible(true);
        });
    }
}
