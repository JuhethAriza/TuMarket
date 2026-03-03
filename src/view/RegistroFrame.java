package view;

import controller.UsuarioController;
import controller.ProductoController;
import controller.CarritoController;
import controller.VentaController;

import model.Usuario;
import model.Vendedor;
import model.Comprador;
import utils.Validator;

import javax.swing.*;
import java.awt.*;

public class RegistroFrame extends JFrame {
    private final UsuarioController usuarioController;
    private final ProductoController productoController;
    private final CarritoController carritoController;
    private final VentaController ventaController;

    private JTextField txtNombre, txtCorreo;
    private JPasswordField txtContraseña;
    private JComboBox<String> cmbTipo;

    public RegistroFrame(UsuarioController usuarioController,
                         ProductoController productoController,
                         CarritoController carritoController,
                         VentaController ventaController) {
        this.usuarioController = usuarioController;
        this.productoController = productoController;
        this.carritoController = carritoController;
        this.ventaController = ventaController;

        setTitle("TuMarket - Registro");
        setSize(450, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10,10));
        JPanel form = new JPanel(new GridLayout(6,1,5,5));
        txtNombre = new JTextField();
        txtCorreo = new JTextField();
        txtContraseña = new JPasswordField();
        cmbTipo = new JComboBox<>(new String[] {"Comprador", "Vendedor"});

        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);
        form.add(new JLabel("Correo:"));
        form.add(txtCorreo);
        form.add(new JLabel("Contraseña:"));
        form.add(txtContraseña);

        JPanel bottom = new JPanel();
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnVolver = new JButton("Volver");
        bottom.add(btnRegistrar);
        bottom.add(btnVolver);

        JPanel tipoPanel = new JPanel();
        tipoPanel.add(new JLabel("Tipo:"));
        tipoPanel.add(cmbTipo);

        add(form, BorderLayout.CENTER);
        add(tipoPanel, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);
        setResizable(false);

        btnRegistrar.addActionListener(e -> registrar());
        btnVolver.addActionListener(e -> {
            LoginFrame login = new LoginFrame(usuarioController, productoController, carritoController, ventaController);
            login.setVisible(true);
            dispose();
        });
    }

    private void registrar() {
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String pass = new String(txtContraseña.getPassword());
        String tipo = (String)cmbTipo.getSelectedItem();

        if (!Validator.esNombreValido(nombre)) {
            JOptionPane.showMessageDialog(this, "Nombre inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!Validator.esCorreoValido(correo)) {
            JOptionPane.showMessageDialog(this, "Correo inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!Validator.esContraseñaValida(pass)) {
            JOptionPane.showMessageDialog(this, "Contraseña inválida (mín 4 caracteres)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario u;
        if ("Vendedor".equals(tipo)) {
            u = new Vendedor(nombre, correo, pass);
        } else {
            u = new Comprador(nombre, correo, pass);
        }

        boolean ok = usuarioController.registrarUsuario(u);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente ✅");
            LoginFrame login = new LoginFrame(usuarioController, productoController, carritoController, ventaController);
            login.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "El correo ya está registrado ❌", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
