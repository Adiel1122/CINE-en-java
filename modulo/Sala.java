package mx.unam.fi.cine.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Representa una sala de cine y administra su conjunto de asientos.
 * Implementa la lógica de distribución de asientos según el tipo de sala.
 */
public class Sala implements Serializable {
    private String nombre; // "Sala A", "Sala B", "Sala VIP"
    private List<Asiento> asientos;

    /**
     * Constructor que inicializa la sala y configura sus asientos automáticamente.
     * @param nombre Identificador de la sala (ej. "Sala A").
     */
    public Sala(String nombre) {
        this.nombre = nombre;
        this.asientos = new ArrayList<>();
        configurarAsientos();
    }

    /**
     * Genera la matriz de asientos según las reglas de negocio del proyecto.
     */
    private void configurarAsientos() {
        // Limpiamos por seguridad
        asientos.clear();

        switch (this.nombre) {
            case "Sala A":
                // Filas A-J (10 filas), 15 asientos c/u [cite: 64]
                generarFilas('A', 'J', 15);
                break;
            case "Sala B":
                // Filas A-D (4 filas), 7 lugares [cite: 66]
                generarFilas('A', 'D', 7);
                // Filas E-J (6 filas), 15 lugares [cite: 67]
                generarFilas('E', 'J', 15);
                break;
            case "Sala VIP":
                // Filas A-H (8 filas), 6 lugares [cite: 68]
                // Nota: Los pasillos son visuales, lógicamente son 6 asientos consecutivos en el modelo.
                generarFilas('A', 'H', 6);
                break;
            default:
                System.out.println("Tipo de sala no reconocido. Se crea vacía.");
        }
    }

    /**
     * Método auxiliar para crear un rango de filas.
     */
    private void generarFilas(char filaInicio, char filaFin, int cantidadAsientos) {
        for (char f = filaInicio; f <= filaFin; f++) {
            for (int i = 1; i <= cantidadAsientos; i++) {
                asientos.add(new Asiento(f, i));
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }
    
    /**
     * Busca un asiento específico en la sala.
     * @return El objeto Asiento o null si no existe.
     */
    public Asiento buscarAsiento(char fila, int numero) {
        for (Asiento a : asientos) {
            if (a.getFila() == fila && a.getNumero() == numero) {
                return a;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Sala: " + nombre + " (Capacidad: " + asientos.size() + " personas)";
    }
}
