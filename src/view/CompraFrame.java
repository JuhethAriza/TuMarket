package view;

import controller.UsuarioController;
import controller.ProductoController;
import controller.CarritoController;
import controller.VentaController;
import model.Producto;
import model.Comprador;

import javax.swing.*;
import java.awt.*;

public class CompraFrame extends JFrame {
    private final Comprador comprador; // if null -> solo vista lectura
    private final UsuarioController usuarioController;
    private final ProductoController productoController;
    private final CarritoController carritoController;
    private final VentaController ventaController;

    private DefaultListModel<Producto> model;
    private JList<Producto> lista;

    public CompraFrame(Comprador comprador,
                       UsuarioController usuarioController,
                       ProductoController productoController,
                       CarritoController carritoController,
                       VentaController ventaController) {
        this.comprador = comprador;
        this.usuarioController = usuarioController;
        this.productoController = productoController;
        this.carritoController = carritoController;
        this.ventaController = ventaController;

        setTitle("TuMarket - Productos");
        setSize(600, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
    }

    private void init() {
        setLayout(new BorderLayout(8,8));
        JLabel lbl = new JLabel("Productos disponibles", JLabel.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        add(lbl, BorderLayout.NORTH);

        model = new DefaultListModel<>();
        lista = new JList<>(model);
        lista.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Producto) {
                    setText(((Producto) value).toString());
                }
                return c;
            }
        });

        JScrollPane scroll = new JScrollPane(lista);
        add(scroll, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        JButton btnComprar = new JButton("Comprar (Agregar al carrito)");
        JButton btnFinalizar = new JButton("Finalizar compra");
        JButton btnVolver = new JButton("Volver");
        botones.add(btnComprar);
        botones.add(btnFinalizar);
        botones.add(btnVolver);

        btnComprar.addActionListener(e -> comprarSeleccionado());
        btnFinalizar.addActionListener(e -> finalizarCompra());
        btnVolver.addActionListener(e -> {
            // volver al home
            if (comprador != null) {
                HomeFrame h = new HomeFrame(comprador, usuarioController, productoController, carritoController, ventaController);
                h.setVisible(true);
            } else {
                LoginFrame login = new LoginFrame(usuarioController, productoController, carritoController, ventaController);
                login.setVisible(true);
            }
            dispose();
        });

        add(botones, BorderLayout.SOUTH);
        cargarProductos();
    }

    private void cargarProductos() {
        model.clear();
        for (Producto p : productoController.obtenerProductos()) {
            model.addElement(p);
        }
    }

    private void comprarSeleccionado() {
        Producto seleccionado = lista.getSelectedValue();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto primero", "Atención", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (comprador == null) {
            JOptionPane.showMessageDialog(this, "Debes iniciar sesión como comprador para comprar", "Atención", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        comprador.getCarrito().agregar(seleccionado);
        JOptionPane.showMessageDialog(this, "Producto añadido al carrito ✅");
    }

    private void finalizarCompra() {
        if (comprador == null) {
            JOptionPane.showMessageDialog(this, "Inicia sesión como comprador para finalizar", "Atención", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    
        double total = comprador.getCarrito().total();
    
        if (total <= 0) {
            JOptionPane.showMessageDialog(this, "Tu carrito está vacío", "Atención", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    
        if (ventaController == null) {
            JOptionPane.showMessageDialog(this, "Error: VentaController no disponible", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String ventaId = "V" + System.currentTimeMillis();
        java.util.List<Producto> productos = new java.util.ArrayList<>(comprador.getCarrito().getItems());
    
        model.Venta venta = new model.Venta(ventaId, comprador, productos, total);
        ventaController.registrarVenta(venta);

        // Limpiar manualmente el carrito, dado que el método limpiar no está definido en CarritoController
        comprador.getCarrito().getItems().clear();

        JOptionPane.showMessageDialog(this, "Compra finalizada ✅\nTotal: $" + total);
    }
}
