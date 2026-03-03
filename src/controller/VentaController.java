package controller;

import model.Venta;
import service.VentaService;
import java.util.List;

public class VentaController {

    private VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    public void registrarVenta(Venta venta) {
        ventaService.registrarVenta(venta);
        System.out.println("Venta registrada: " + venta);
    }

    public List<Venta> getVentas() {
        return ventaService.obtenerVentas();
    }
}
