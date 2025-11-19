package mx.unam.fi.cine.modelo;

/**
 * Representa un empleado encargado de la dulcería.
 * Se encarga de preparar las órdenes.
 */
public class VendedorDulceria extends Empleado {
    private String diaDescanso;

    /**
     * Constructor de VendedorDulceria.
     * @param diaDescanso Día de la semana que descansa[cite: 80].
     */
    public VendedorDulceria(String nombre, String apPaterno, String apMaterno, int edad,
                            String nickname, String password, String email, String telefono,
                            Turno turno, String diaDescanso) {
        super(nombre, apPaterno, apMaterno, edad, nickname, password, email, telefono, turno);
        this.diaDescanso = diaDescanso;
    }

    public String getDiaDescanso() { return diaDescanso; }
    public void setDiaDescanso(String diaDescanso) { this.diaDescanso = diaDescanso; }

    @Override
    public String toString() {
        return "Vendedor Dulcería: " + super.getNickname() + " [Descansa: " + diaDescanso + "]";
    }
}
