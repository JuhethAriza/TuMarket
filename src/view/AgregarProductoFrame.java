package view;

import controller.UsuarioController;
import controller.ProductoController;
import controller.CarritoController;
import controller.VentaController;
import model.Vendedor;
import model.Producto;

import javax.swing.*;
import java.awt.*;

public class AgregarProductoFrame extends JFrame {
    private final Vendedor vendedor;
    private final UsuarioController usuarioController;
    private final ProductoController productoController;
    private final CarritoController carritoController;
    private final VentaController ventaController;

    private JTextField txtNombre, txtPrecio;
    private JTextArea txtDescripcion;

    public AgregarProductoFrame(Vendedor vendedor, UsuarioController usuarioController, ProductoController productoController, CarritoController carritoController, VentaController ventaController) {
        this.vendedor = vendedor;
        this.usuarioController = usuarioController;
        this.productoController = productoController;
        this.carritoController = carritoController;
        this.ventaController = ventaController;

        setTitle("TuMarket - Agregar producto");
        setSize(450, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
    }

    private void init() {
        setLayout(new BorderLayout(8,8));
        JPanel form = new JPanel(new GridLayout(6,1,5,5));
        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtDescripcion = new JTextArea(4, 20);

        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);
        form.add(new JLabel("Precio:"));
        form.add(txtPrecio);
        form.add(new JLabel("Descripción:"));
        form.add(new JScrollPane(txtDescripcion));

        JPanel botones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnVolver = new JButton("Volver");
        botones.add(btnAgregar);
        botones.add(btnVolver);

        btnAgregar.addActionListener(e -> agregarProducto());
        btnVolver.addActionListener(e -> {
            HomeFrame h = new HomeFrame(vendedor, usuarioController, productoController, carritoController, ventaController);
            h.setVisible(true);
            dispose();
        });

        add(form, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    private void agregarProducto() {
        String nombre = txtNombre.getText().trim();
        String precioStr = txtPrecio.getText().trim();
        String descripcion = txtDescripcion.getText().trim();

        if (nombre.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa nombre y precio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Producto p = new Producto(nombre, precio, descripcion, vendedor);
        productoController.agregarProducto(p);
        JOptionPane.showMessageDialog(this, "Producto agregado correctamente ✅");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtDescripcion.setText("");

    }
}
