package model;

public class Comprador extends Usuario {
    private final Carrito carrito = new Carrito();

    public Comprador(String nombre, String correo, String contraseña) {
        super(nombre, correo, contraseña);
    }

    public Carrito getCarrito() { return carrito; }

    @Override
    public void mostrarInformacion() {
        System.out.println("Comprador: " + nombre + " - carrito items: " + carrito.getItems().size());
    }
}
