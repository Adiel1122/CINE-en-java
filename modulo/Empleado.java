package mx.unam.fi.cine.modelo;

/**
 * Clase base para cualquier empleado del cine.
 * Maneja el turno laboral.
 */
public abstract class Empleado extends Usuario {
    // Usamos un Enum para restringir los valores posibles del turno
    public enum Turno { MATUTINO, VESPERTINO, NOCTURNO }

    private Turno turno;

    /**
     * Constructor de Empleado.
     * @param turno Turno laboral (Matutino, Vespertino, Nocturno)[cite: 78].
     */
    public Empleado(String nombre, String apPaterno, String apMaterno, int edad,
                    String nickname, String password, String email, String telefono,
                    Turno turno) {
        super(nombre, apPaterno, apMaterno, edad, nickname, password, email, telefono);
        this.turno = turno;
    }

    public Turno getTurno() { return turno; }
    public void setTurno(Turno turno) { this.turno = turno; }
}
