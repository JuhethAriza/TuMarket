package model;

public abstract class Usuario {
    protected String nombre;
    protected String correo;
    protected String contraseña;

    public Usuario(String nombre, String correo, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    // devuelven el valor de un atributo protegido 
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getContraseña() { return contraseña; }

    // modifican el valor de un atributo protegido.
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public abstract void mostrarInformacion();
}
