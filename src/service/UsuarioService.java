package service;

import model.Usuario;
import java.util.*;

public class UsuarioService {
    private final Map<String, Usuario> usuariosByCorreo = new HashMap<>();

    public boolean guardarUsuario(Usuario u) {
        if (u == null || u.getCorreo() == null) return false;
        String key = u.getCorreo().toLowerCase();
        if (usuariosByCorreo.containsKey(key)) return false;
        usuariosByCorreo.put(key, u);
        return true;
    }

    public Usuario obtenerPorCorreo(String correo) {
        if (correo == null) return null;
        return usuariosByCorreo.get(correo.toLowerCase());
    }

    public Collection<Usuario> obtenerTodos() {
        return usuariosByCorreo.values();
    }
}
