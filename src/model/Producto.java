package model;

public class Producto {
    private static long contador = 1;
    private final String id;
    private String nombre;
    private double precio;
    private String descripcion;
    private Vendedor vendedor;

    public Producto(String nombre, double precio, String descripcion, Vendedor vendedor) {
        this.id = String.valueOf(contador++);
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion == null ? "" : descripcion;
        this.vendedor = vendedor;
    }

    public Producto(int i, String string, float f) {
        this.id = String.valueOf(contador++);
        this.nombre = string;
        this.precio = f;
        this.descripcion = "";
        this.vendedor = null;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getDescripcion() { return descripcion; }
    public Vendedor getVendedor() { return vendedor; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setVendedor(Vendedor vendedor) { this.vendedor = vendedor; }

    @Override
    public String toString() {
        String vend = vendedor != null ? vendedor.getNombre() : "Anónimo";
        return nombre + " - $" + precio + " (por " + vend + ")";
    }
}
