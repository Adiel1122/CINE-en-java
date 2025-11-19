package mx.unam.fi.cine.modelo;

import java.io.*;

public class Producto implements Serializable {
    private String nombre; // Ej: "Palomitas Jumbo Mantequilla"
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() {
        return String.format("%s ($%.2f)", nombre, precio);
    }
}
