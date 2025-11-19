package mx.unam.fi.cine.modelo;

/**
 * Representa un cliente del cine.
 * Añade información bancaria para realizar compras.
 */
public class Cliente extends Usuario {
    private String numeroTarjeta; // String para conservar ceros a la izquierda y no realizar operaciones matemáticas

    /**
     * Constructor de Cliente.
     * @param numeroTarjeta Número de tarjeta bancaria (16 dígitos)[cite: 27].
     */
    public Cliente(String nombre, String apPaterno, String apMaterno, int edad,
                   String nickname, String password, String email, String telefono,
                   String numeroTarjeta) {
        super(nombre, apPaterno, apMaterno, edad, nickname, password, email, telefono);
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNumeroTarjeta() { return numeroTarjeta; }
    public void setNumeroTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }

    @Override
    public String toString() {
        return "Cliente: " + super.toString();
    }
}
