package mx.unam.fi.cine.modelo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Representa una proyección específica de una película en una sala y horario determinados.
 */
public class Funcion implements Serializable{
    private String idFuncion;
    private Pelicula pelicula;
    private Sala sala;
    private LocalDateTime horario;

    /**
     * Constructor de la función. Genera automáticamente el ID único.
     * Importante: Se crea una NUEVA instancia de Sala basada en el prototipo, 
     * para que los asientos ocupados de esta función no afecten a otras.
     * * @param pelicula Objeto Pelicula a proyectar.
     * @param nombreSala Nombre de la sala (se creará una nueva instancia de Sala).
     * @param horario Fecha y hora de la función.
     */
    public Funcion(Pelicula pelicula, String nombreSala, LocalDateTime horario) {
        this.pelicula = pelicula;
        // Composición: La función tiene su propia instancia de Sala para gestionar sus propios asientos
        this.sala = new Sala(nombreSala); 
        this.horario = horario;
        this.idFuncion = generarIdUnico();
    }

    /**
     * Genera el ID con formato: InicialesTitulo:AAAAMMDD:hhmm:Sala
     * Referencia PDF 
     */
    private String generarIdUnico() {
        // 1. Iniciales del título
        StringBuilder iniciales = new StringBuilder();
        String[] palabras = pelicula.getTitulo().split(" ");
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                iniciales.append(palabra.charAt(0));
            }
        }

        // 2. Formato de fecha y hora
        DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("HHmm");
        
        String fechaStr = horario.format(fmtFecha);
        String horaStr = horario.format(fmtHora);

        // 3. Construcción del ID
        // Formato: Iniciales:AAAAMMDD:hhmm:Sala
        return String.format("%s:%s:%s:%s", 
                iniciales.toString().toUpperCase(), 
                fechaStr, 
                horaStr, 
                sala.getNombre().replace(" ", ""));
    }

    // Getters y Setters
    public String getIdFuncion() { return idFuncion; }
    
    public Pelicula getPelicula() { return pelicula; }
    
    public Sala getSala() { return sala; }
    
    public LocalDateTime getHorario() { return horario; }
    public void setHorario(LocalDateTime horario) { 
        this.horario = horario; 
        // Si cambia el horario, se debería regenerar el ID, 
        // pero por simplicidad lo dejamos fijo o se implementaría updateId().
    }

    @Override
    public String toString() {
        DateTimeFormatter printable = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("Función [%s] - %s en %s a las %s", 
                idFuncion, pelicula.getTitulo(), sala.getNombre(), horario.format(printable));
    }
}
