package view;

import controller.UsuarioController;
import controller.ProductoController;
import controller.CarritoController;
import controller.VentaController;
import model.Usuario;
import model.Vendedor;
import model.Comprador;
import model.Producto;

import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {
    private final Usuario usuario;
    private final UsuarioController usuarioController;
    private final ProductoController productoController;
    private final CarritoController carritoController;
    private final VentaController ventaController;

    private DefaultListModel<String> listaModel;
    private JList<String> listaProductos;

    public HomeFrame(Usuario usuario,
                     UsuarioController usuarioController,
                     ProductoController productoController,
                     CarritoController carritoController,
                     VentaController ventaController) {
        this.usuario = usuario;
        this.usuarioController = usuarioController;
        this.productoController = productoController;
        this.carritoController = carritoController;
        this.ventaController = ventaController;

        setTitle("TuMarket - Home (" + usuario.getNombre() + ")");
        setSize(600, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
    }

    private void init() {
        setLayout(new BorderLayout(10,10));
        JLabel lbl = new JLabel("Bienvenido a TuMarket, " + usuario.getNombre(), JLabel.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        add(lbl, BorderLayout.NORTH);

        listaModel = new DefaultListModel<>();
        listaProductos = new JList<>(listaModel);
        JScrollPane scroll = new JScrollPane(listaProductos);
        add(scroll, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        JButton btnAgregar = new JButton("Agregar producto");
        JButton btnVer = new JButton("Ver / Comprar");
        JButton btnCerrar = new JButton("Cerrar sesión");

        botones.add(btnAgregar);
        botones.add(btnVer);
        botones.add(btnCerrar);

        if (usuario instanceof Comprador) {
            btnAgregar.setEnabled(false);
        }

        btnAgregar.addActionListener(e -> {
            if (usuario instanceof Vendedor) {
                AgregarProductoFrame ap = new AgregarProductoFrame((Vendedor) usuario, usuarioController, productoController, carritoController, ventaController);
                ap.setVisible(true);
                dispose();
            }
        });

        btnVer.addActionListener(e -> {
            if (usuario instanceof Comprador) {
                CompraFrame c = new CompraFrame((Comprador) usuario, usuarioController, productoController, carritoController, ventaController);
                c.setVisible(true);
                dispose();
            } else {
                CompraFrame c = new CompraFrame(null, usuarioController, productoController, carritoController, ventaController);
                c.setVisible(true);
                dispose();
            }
        });

        btnCerrar.addActionListener(e -> {
            LoginFrame login = new LoginFrame(usuarioController, productoController, carritoController, ventaController);
            login.setVisible(true);
            dispose();
        });

        add(botones, BorderLayout.SOUTH);
        actualizarLista();
    }

    public void actualizarLista() {
        listaModel.clear();
        for (Producto p : productoController.obtenerProductos()) {
            listaModel.addElement(p.toString());
        }
    }
}

