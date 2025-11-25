package mx.unam.fi.cine.modelo;

/**
 * Representa al usuario final externo que interactúa con el sistema para adquirir servicios.
 * <p>
 * Esta clase concreta extiende de {@link Usuario} para agregar la capacidad financiera necesaria
 * para realizar transacciones. Es la entidad principal que alimenta el flujo de ingresos del sistema.
 * </p>
 * <b>Roles y Responsabilidades:</b>
 * <ul>
 * <li><b>Consumidor:</b> Es el único actor autorizado para iniciar el flujo de compra de boletos en {@code ControladorCompra}.</li>
 * <li><b>Entidad Financiera:</b> Almacena los datos bancarios (simulados) requeridos para la validación de pagos.</li>
 * </ul>
 *
 * @author Equipo CineByt
 * @version 1.0
 * @see mx.unam.fi.cine.modelo.Usuario
 * @see mx.unam.fi.cine.controlador.ControladorCompra
 */
public class Cliente extends Usuario {

    /**
     * Identificador de versión para la serialización.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Número de la tarjeta bancaria asociada al cliente.
     * <p>
     * <b>Decisión de Diseño:</b> Se utiliza {@code String} en lugar de tipos numéricos ({@code long} o {@code int}) por tres razones:
     * <ol>
     * <li><b>Integridad:</b> Preserva los ceros a la izquierda que son significativos en identificadores bancarios.</li>
     * <li><b>Naturaleza:</b> No se realizan operaciones aritméticas con este dato.</li>
     * <li><b>Capacidad:</b> Evita problemas de desbordamiento (overflow) con números de 16 dígitos en arquitecturas de 32 bits.</li>
     * </ol>
     */
    private String numeroTarjeta; 

    /**
     * Constructor principal para registrar un nuevo Cliente.
     * <p>
     * Recibe todos los datos demográficos y de acceso requeridos por la jerarquía {@link Usuario} y {@link Persona},
     * además del instrumento de pago.
     *
     * @param nombre        Nombre de pila.
     * @param apPaterno     Apellido paterno.
     * @param apMaterno     Apellido materno.
     * @param edad          Edad del cliente (útil para validación de clasificaciones de películas).
     * @param nickname      Nombre de usuario para Login.
     * @param password      Contraseña de acceso.
     * @param email         Correo para envío de boletos.
     * @param telefono      Teléfono de contacto.
     * @param numeroTarjeta Número de tarjeta (se espera una cadena de 16 dígitos).
     */
    public Cliente(String nombre, String apPaterno, String apMaterno, int edad,
                   String nickname, String password, String email, String telefono,
                   String numeroTarjeta) {
        // Inicializa la parte de Usuario/Persona
        super(nombre, apPaterno, apMaterno, edad, nickname, password, email, telefono);
        // Inicializa el atributo específico de Cliente
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * Obtiene el número de tarjeta registrado.
     * @return Cadena con los dígitos de la tarjeta.
     */
    public String getNumeroTarjeta() { return numeroTarjeta; }

    /**
     * Actualiza el número de tarjeta bancaria.
     * @param numeroTarjeta Nueva cadena de dígitos.
     */
    public void setNumeroTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }

    /**
     * Representación textual del Cliente.
     * <p>
     * Antepone la etiqueta "Cliente: " a la representación estándar de {@link Usuario}
     * para distinguirlo explícitamente de los empleados en listas polimórficas.
     *
     * @return Cadena formato "Cliente: Nombre Apellidos (Nickname)".
     */
    @Override
    public String toString() {
        return "Cliente: " + super.toString();
    }
}
