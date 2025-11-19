package mx.unam.fi.cine.modelo;

import java.io.*;

/**
 * Clase base abstracta que representa a un ser humano en el sistema.
 * Define atributos comunes como nombre y edad.
 */
public abstract class Persona implements Serializable {
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private int edad;

    /**
     * Constructor de Persona.
     * @param nombre Nombre(s) de la persona[cite: 21, 73].
     * @param apPaterno Apellido paterno[cite: 21, 73].
     * @param apMaterno Apellido materno[cite: 21, 73].
     * @param edad Edad de la persona[cite: 22].
     */
    public Persona(String nombre, String apPaterno, String apMaterno, int edad) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.edad = edad;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApPaterno() { return apPaterno; }
    public void setApPaterno(String apPaterno) { this.apPaterno = apPaterno; }

    public String getApMaterno() { return apMaterno; }
    public void setApMaterno(String apMaterno) { this.apMaterno = apMaterno; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    @Override
    public String toString() {
        return nombre + " " + apPaterno + " " + apMaterno;
    }
}
