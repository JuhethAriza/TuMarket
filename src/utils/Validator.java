package utils;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public static boolean esCorreoValido(String correo) {
        return correo != null && EMAIL.matcher(correo).matches();
    }

    public static boolean esContraseñaValida(String pass) {
        return pass != null && pass.length() >= 4;
    }

    public static boolean esNombreValido(String nombre) {
        return nombre != null && !nombre.trim().isEmpty();
    }
}
