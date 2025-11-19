package mx.unam.fi.cine.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Combo implements Serializable {
    private String nombre; // Ej: "Combo Amix"
    private List<Producto> productos;

    public Combo(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    /**
     * Calcula el precio total sumando los productos y restando el 10%.
     * [cite_start]Regla de negocio[cite: 138].
     */
    public double calcularPrecioTotal() {
        double suma = 0;
        for (Producto p : productos) {
            suma += p.getPrecio();
        }
        return suma * 0.90; // Aplica 10% de descuento
    }

    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return String.format("%s (Incluye %d productos) - Precio Final: $%.2f", 
                nombre, productos.size(), calcularPrecioTotal());
    }
}
