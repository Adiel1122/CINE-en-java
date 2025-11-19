package mx.unam.fi.cine.modelo;

import java.io.*;

/**
 * Representa la información general de una película disponible en cartelera.
 */
public class Pelicula implements Serializable{
    private String titulo;
    private String genero;
    private String sinopsis;
    private int duracionMinutos; // Almacenamos en minutos para facilitar cálculos

    /**
     * Constructor de Película.
     * @param titulo Título de la película.
     * @param genero Género(s) de la película.
     * @param sinopsis Breve descripción.
     * @param duracionMinutos Duración total en minutos.
     */
    public Pelicula(String titulo, String genero, String sinopsis, int duracionMinutos) {
        this.titulo = titulo;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.duracionMinutos = duracionMinutos;
    }

    // Getters y Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getSinopsis() { return sinopsis; }
    public void setSinopsis(String sinopsis) { this.sinopsis = sinopsis; }

    public int getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }

    /**
     * Retorna la duración en formato hh:mm para visualización.
     * @return Cadena formateada.
     */
    public String getDuracionFormato() {
        int horas = duracionMinutos / 60;
        int minutos = duracionMinutos % 60;
        return String.format("%02d:%02d", horas, minutos);
    }

    @Override
    public String toString() {
        return String.format("Título: %s | Género: %s | Duración: %s", 
                titulo, genero, getDuracionFormato());
    }
}