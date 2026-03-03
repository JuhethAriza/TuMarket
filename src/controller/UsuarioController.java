package controller;

import controller.interfaces.IUsuarioController;
import model.Usuario;
import service.UsuarioService;

public class UsuarioController implements IUsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @Override
    public Usuario iniciarSesion(String correo, String contraseña) {
        Usuario u = usuarioService.obtenerPorCorreo(correo);
        if (u == null) return null;
        if (u.getContraseña().equals(contraseña)) return u;
        return null;
    }

    public Usuario obtenerPorCorreo(String correo) {
        return usuarioService.obtenerPorCorreo(correo);
    }

    public java.util.Collection<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }
}
