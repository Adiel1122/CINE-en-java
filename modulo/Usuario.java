package mx.unam.fi.cine.modelo;

import java.io.*;

/**
 * Clase intermedia que añade credenciales y datos de contacto a una Persona.
 */
public abstract class Usuario extends Persona implements Serializable {
    private String nickname;
    private String password;
    private String email;
    private String telefono;

    /**
     * Constructor de Usuario.
     * Utiliza super() para inicializar los datos de Persona.
     * @param nickname Nombre de usuario único[cite: 23, 74].
     * @param password Contraseña[cite: 24, 75].
     * @param email Correo electrónico[cite: 25, 76].
     * @param telefono Número celular[cite: 26, 77].
     */
    public Usuario(String nombre, String apPaterno, String apMaterno, int edad,
                   String nickname, String password, String email, String telefono) {
        super(nombre, apPaterno, apMaterno, edad); // Llama al constructor de Persona
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return super.toString() + " (" + nickname + ")";
    }
}
