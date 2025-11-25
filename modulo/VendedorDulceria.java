package mx.unam.fi.cine.modelo;

/**
 * Representa al personal operativo encargado del área de alimentos y bebidas en <b>CineByt</b>.
 * <p>
 * Esta clase concreta extiende a {@link Empleado} para modelar a los trabajadores que interactúan
 * con el inventario de productos.
 * </p>
 * <b>Rol Arquitectónico y Concurrencia:</b>
 * <br>
 * En la lógica del negocio, el {@code VendedorDulceria} es el actor responsable de iniciar
 * el flujo de ventas que deriva en la ejecución del hilo {@code PreparacionDulceria}.
 * Aunque esta clase solo almacena los datos del empleado, su existencia valida el acceso
 * al {@code ControladorDulceria}.
 *
 * @author Equipo CineByt
 * @version 1.0
 * @see mx.unam.fi.cine.modelo.Empleado
 * @see mx.unam.fi.cine.controlador.ControladorDulceria
 */
public class VendedorDulceria extends Empleado {

    /**
     * Identificador de versión para la serialización.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Día de la semana asignado para el descanso del vendedor (ej. "Lunes", "Martes").
     * <p>
     * Este atributo es informativo para la gestión de recursos humanos y podría ser utilizado
     * para validar el Login en días no laborales en versiones futuras.
     */
    private String diaDescanso;

    /**
     * Constructor principal para registrar un nuevo Vendedor de Dulcería.
     * <p>
     * Inicializa la jerarquía completa de herencia (Persona -> Usuario -> Empleado)
     * y asigna el atributo específico de descanso.
     *
     * @param nombre      Nombre de pila.
     * @param apPaterno   Apellido paterno.
     * @param apMaterno   Apellido materno.
     * @param edad        Edad del vendedor.
     * @param nickname    Usuario de acceso al sistema.
     * @param password    Contraseña.
     * @param email       Correo de contacto.
     * @param telefono    Teléfono móvil.
     * @param turno       Turno laboral ({@link mx.unam.fi.cine.modelo.Empleado.Turno}).
     * @param diaDescanso Día de la semana libre (String).
     */
    public VendedorDulceria(String nombre, String apPaterno, String apMaterno, int edad,
                            String nickname, String password, String email, String telefono,
                            Turno turno, String diaDescanso) {
        super(nombre, apPaterno, apMaterno, edad, nickname, password, email, telefono, turno);
        this.diaDescanso = diaDescanso;
    }

    // ----------------------------------------------------------------------------------
    // Métodos de Acceso (Getters y Setters)
    // ----------------------------------------------------------------------------------

    /**
     * Obtiene el día de descanso del vendedor.
     * @return Cadena con el día.
     */
    public String getDiaDescanso() { return diaDescanso; }

    /**
     * Actualiza el día de descanso.
     * @param diaDescanso Nuevo día de descanso.
     */
    public void setDiaDescanso(String diaDescanso) { this.diaDescanso = diaDescanso; }

    /**
     * Representación textual del Vendedor.
     * <p>
     * Útil para paneles de administración donde se requiere ver rápidamente
     * quién está atendiendo y cuándo descansa.
     *
     * @return Cadena formato: "Vendedor Dulcería: Nickname [Descansa: Día]".
     */
    @Override
    public String toString() {
        return "Vendedor Dulcería: " + super.getNickname() + " [Descansa: " + diaDescanso + "]";
    }
}
