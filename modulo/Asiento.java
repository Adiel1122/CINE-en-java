package mx.unam.fi.cine.modelo;

import java.io.*;

/**
 * Representa un asiento individual dentro de una sala de cine.
 * Mantiene su ubicación (fila y número) y su estado actual.
 */
public class Asiento implements Serializable {
    private char fila;
    private int numero;
    private boolean ocupado; // true si está vendido/ocupado, false si está libre

    /**
     * Constructor del asiento.
     * @param fila Letra de la fila.
     * @param numero Número del asiento.
     */
    public Asiento(char fila, int numero) {
        this.fila = fila;
        this.numero = numero;
        this.ocupado = false; // Por defecto está libre
    }

    public char getFila() {
        return fila;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    @Override
    public String toString() {
        return String.format("%c%d [%s]", fila, numero, (ocupado ? "Ocupado" : "Libre"));
    }
}