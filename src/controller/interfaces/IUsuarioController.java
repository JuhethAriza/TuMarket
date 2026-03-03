package controller.interfaces;

import model.Usuario;

public interface IUsuarioController {
    boolean registrarUsuario(Usuario usuario);
    Usuario iniciarSesion(String correo, String contraseña);
}
