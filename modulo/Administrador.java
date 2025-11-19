package mx.unam.fi.cine.modelo;

/**
 * Representa un administrador del sistema.
 * Tiene permisos para dar de alta películas y funciones.
 */
public class Administrador extends Empleado {
    private boolean esFinDeSemana; // true = Fin de semana, false = Entre semana

    /**
     * Constructor de Administrador.
     * @param esFinDeSemana Define si es administrador de fin de semana o entre semana[cite: 81].
     */
    public Administrador(String nombre, String apPaterno, String apMaterno, int edad,
                         String nickname, String password, String email, String telefono,
                         Turno turno, boolean esFinDeSemana) {
        super(nombre, apPaterno, apMaterno, edad, nickname, password, email, telefono, turno);
        this.esFinDeSemana = esFinDeSemana;
    }

    public boolean isEsFinDeSemana() { return esFinDeSemana; }
    public void setEsFinDeSemana(boolean esFinDeSemana) { this.esFinDeSemana = esFinDeSemana; }

    @Override
    public String toString() {
        String tipo = esFinDeSemana ? "Fin de Semana" : "Entre Semana";
        return "Admin (" + tipo + "): " + super.getNickname();
    }
}