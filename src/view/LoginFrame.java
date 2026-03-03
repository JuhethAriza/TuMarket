package view;

import controller.UsuarioController;
import controller.ProductoController;
import controller.CarritoController;
import controller.VentaController;
import model.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final UsuarioController usuarioController;
    private final ProductoController productoController;
    private final CarritoController carritoController;
    private final VentaController ventaController;

    private JTextField txtCorreo;
    private JPasswordField txtContrasena;

    public LoginFrame(UsuarioController usuarioController, ProductoController productoController, CarritoController carritoController, VentaController ventaController) {
        this.usuarioController = usuarioController;
        this.productoController = productoController;
        this.carritoController = carritoController;
        this.ventaController = ventaController;

        setTitle("TuMarket - Iniciar sesión");
        setSize(420, 260);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel main = new JPanel(new BorderLayout(10,10));
        JPanel form = new JPanel(new GridLayout(4,1,5,5));

        txtCorreo = new JTextField();
        txtContrasena = new JPasswordField();

        form.add(new JLabel("Correo:"));
        form.add(txtCorreo);
        form.add(new JLabel("Contraseña:"));
        form.add(txtContrasena);

        JPanel botones = new JPanel();
        JButton btnLogin = new JButton("Iniciar sesión");
        JButton btnRegistro = new JButton("Registrarse");
        botones.add(btnLogin);
        botones.add(btnRegistro);

        btnLogin.addActionListener(e -> doLogin());
        btnRegistro.addActionListener(e -> {
            RegistroFrame reg = new RegistroFrame(usuarioController, productoController, carritoController, ventaController);
            reg.setVisible(true);
            dispose();
        });

        main.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        main.add(form, BorderLayout.CENTER);
        main.add(botones, BorderLayout.SOUTH);

        setContentPane(main);
    }

    private void doLogin() {
        String correo = txtCorreo.getText().trim();
        String pass = new String(txtContrasena.getPassword());

        Usuario u = usuarioController.iniciarSesion(correo, pass);
        if (u != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido a TuMarket, " + u.getNombre());
            HomeFrame home = new HomeFrame(u, usuarioController, productoController, carritoController, ventaController);
            home.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales inválidas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
