package mx.unam.fi.cine.modelo;

/**
 * Representa el rol de gestión operativa con privilegios elevados dentro del sistema <b>CineByt</b>.
 * <p>
 * Esta clase concreta extiende a {@link Empleado} para modelar a los encargados de la configuración
 * y mantenimiento del negocio. A diferencia de otros actores, el Administrador no realiza transacciones
 * de venta directa, sino que gestiona los recursos que permiten dichas ventas.
 * </p>
 * <b>Privilegios y Responsabilidades:</b>
 * <ul>
 * <li><b>Gestión de Cartelera:</b> Alta de nuevas instancias de {@link Pelicula}.</li>
 * <li><b>Programación:</b> Creación de objetos {@link Funcion}, validando reglas de negocio (no cruce de horarios).</li>
 * <li><b>Auditoría:</b> Acceso a reportes de ventas y ocupación de salas.</li>
 * </ul>
 * * @author Equipo CineByt
 * @version 1.0
 * @see mx.unam.fi.cine.modelo.Empleado
 * @see mx.unam.fi.cine.controlador.ControladorAdministrador
 */
public class Administrador extends Empleado {

    /**
     * Identificador de versión para la serialización.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Bandera que determina el perfil de disponibilidad del administrador.
     * <p>
     * Este atributo permite segmentar la lógica de negocio o los reportes según el esquema laboral:
     * <ul>
     * <li>{@code true}: <b>Admin de Fin de Semana</b> (Alta demanda, enfoque en resolución de conflictos inmediatos).</li>
     * <li>{@code false}: <b>Admin Entre Semana</b> (Enfoque en planificación y mantenimiento de cartelera).</li>
     * </ul>
     */
    private boolean esFinDeSemana;

    /**
     * Constructor principal para crear un perfil de Administrador.
     * <p>
     * Inicializa toda la cadena de herencia (Persona -> Usuario -> Empleado) y establece
     * el perfil de horario específico.
     *
     * @param nombre        Nombre de pila.
     * @param apPaterno     Apellido paterno.
     * @param apMaterno     Apellido materno.
     * @param edad          Edad del administrador.
     * @param nickname      Nombre de usuario para Login (con permisos de admin).
     * @param password      Contraseña de acceso.
     * @param email         Correo institucional.
     * @param telefono      Teléfono de contacto.
     * @param turno         Turno asignado ({@link mx.unam.fi.cine.modelo.Empleado.Turno}).
     * @param esFinDeSemana {@code true} si el rol es para Sábados/Domingos, {@code false} para Lunes-Viernes.
     */
    public Administrador(String nombre, String apPaterno, String apMaterno, int edad,
                         String nickname, String password, String email, String telefono,
                         Turno turno, boolean esFinDeSemana) {
        super(nombre, apPaterno, apMaterno, edad, nickname, password, email, telefono, turno);
        this.esFinDeSemana = esFinDeSemana;
    }

    // ----------------------------------------------------------------------------------
    // Métodos de Acceso (Getters y Setters)
    // ----------------------------------------------------------------------------------

    /**
     * Verifica si el administrador pertenece al esquema de fin de semana.
     * @return {@code true} para fines de semana, {@code false} para días hábiles.
     */
    public boolean isEsFinDeSemana() { return esFinDeSemana; }

    /**
     * Modifica el esquema de horario del administrador.
     * @param esFinDeSemana El nuevo estado del perfil.
     */
    public void setEsFinDeSemana(boolean esFinDeSemana) { this.esFinDeSemana = esFinDeSemana; }

    /**
     * Representación textual del Administrador para logs y paneles de control.
     * <p>
     * Muestra el tipo de gestión (Fin de Semana vs Entre Semana) junto con el nickname
     * para una identificación rápida en el sistema.
     *
     * @return Cadena formato "Admin (Tipo): Nickname".
     */
    @Override
    public String toString() {
        String tipo = esFinDeSemana ? "Fin de Semana" : "Entre Semana";
        return "Admin (" + tipo + "): " + super.getNickname();
    }
}
