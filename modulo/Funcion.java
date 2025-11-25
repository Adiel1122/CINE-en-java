package mx.unam.fi.cine.modelo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa una proyección específica (evento) dentro de la cartelera del sistema <b>CineByt</b>.
 * <p>
 * Esta clase funciona como una entidad agregadora que vincula tres dimensiones:
 * 1. <b>Qué:</b> La {@link Pelicula} a proyectar.
 * 2. <b>Dónde:</b> Una instancia única de {@link Sala}.
 * 3. <b>Cuándo:</b> El {@link LocalDateTime} de inicio.
 * </p>
 * * <h3>Arquitectura y Gestión de Estado:</h3>
 * Un aspecto fundamental de esta clase es su manejo de la <b>Sala</b>. Implementa una relación
 * de <b>Composición Fuerte</b>: cada Función instancia su propia copia de un objeto {@code Sala}.
 * <br>
 * Esto es crucial para el sistema de ventas: permite que el Asiento A1 esté "Ocupado" 
 * en la función de las 18:00, pero "Libre" en la función de las 20:00, aunque físicamente sea el mismo lugar.
 *
 * @author Equipo CineByt
 * @version 1.0
 * @see mx.unam.fi.cine.modelo.Pelicula
 * @see mx.unam.fi.cine.modelo.Sala
 * @see mx.unam.fi.cine.controlador.ControladorAdministrador
 */
public class Funcion implements Serializable {

    /**
     * Identificador de versión para la serialización.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único generado algorítmicamente.
     * Formato: {@code INICIALES:FECHA:HORA:SALA}
     * Permite búsquedas rápidas y validaciones de unicidad sin comparar todos los campos.
     */
    private String idFuncion;

    /**
     * Referencia a la película que se proyectará.
     */
    private Pelicula pelicula;

    /**
     * Instancia dedicada de la sala para esta función específica.
     * Contiene la matriz de asientos ({@link Asiento}) cuyo estado (libre/ocupado)
     * es exclusivo de esta proyección.
     */
    private Sala sala;

    /**
     * Fecha y hora exacta de inicio de la proyección.
     * Utilizado para validaciones de cruces de horario en el {@code ControladorAdministrador}.
     */
    private LocalDateTime horario;

    /**
     * Constructor principal de la Función.
     * <p>
     * Realiza dos tareas críticas de inicialización:
     * <ol>
     * <li><b>Instanciación de Sala:</b> Crea un nuevo objeto {@code Sala} basado en el nombre proporcionado.
     * Esto garantiza que la gestión de asientos sea independiente de otras funciones.</li>
     * <li><b>Generación de ID:</b> Construye el identificador único basado en los datos inmutables iniciales.</li>
     * </ol>
     *
     * @param pelicula    Objeto {@link Pelicula} con la información de la cinta.
     * @param nombreSala  Nombre de la sala (ej. "Sala A"). Se utiliza como "factory" para crear la instancia.
     * @param horario     Objeto {@link LocalDateTime} con la fecha y hora de inicio.
     */
    public Funcion(Pelicula pelicula, String nombreSala, LocalDateTime horario) {
        this.pelicula = pelicula;
        // Composición: La función tiene su propia instancia de Sala para gestionar sus propios asientos de forma aislada
        this.sala = new Sala(nombreSala); 
        this.horario = horario;
        this.idFuncion = generarIdUnico();
    }

    /**
     * Genera un identificador único (Clave Primaria lógica) para la función.
     * <p>
     * El algoritmo de construcción es:
     * <ul>
     * <li><b>Iniciales:</b> Primera letra de cada palabra del título (ej. "Star Wars" -> "SW").</li>
     * <li><b>Fecha:</b> Formato AAAAMMDD (ej. "20231025").</li>
     * <li><b>Hora:</b> Formato HHmm (ej. "1830").</li>
     * <li><b>Sala:</b> Nombre de la sala sin espacios (ej. "SalaA").</li>
     * </ul>
     *
     * @return Una cadena con el formato {@code INICIALES:AAAAMMDD:HHmm:SALA}.
     */
    private String generarIdUnico() {
        // 1. Obtención de iniciales del título
        StringBuilder iniciales = new StringBuilder();
        String[] palabras = pelicula.getTitulo().split(" ");
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                iniciales.append(palabra.charAt(0));
            }
        }

        // 2. Formato de fecha y hora para cadena compacta
        DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("HHmm");
        
        String fechaStr = horario.format(fmtFecha);
        String horaStr = horario.format(fmtHora);

        // 3. Construcción del ID final concatenado
        return String.format("%s:%s:%s:%s", 
                iniciales.toString().toUpperCase(), 
                fechaStr, 
                horaStr, 
                sala.getNombre().replace(" ", ""));
    }

    // ----------------------------------------------------------------------------------
    // Métodos de Acceso (Getters y Setters)
    // ----------------------------------------------------------------------------------

    /**
     * Obtiene el ID único de la función.
     * @return Cadena identificadora.
     */
    public String getIdFuncion() { return idFuncion; }
    
    /**
     * Obtiene la película asociada.
     * @return Objeto Pelicula.
     */
    public Pelicula getPelicula() { return pelicula; }
    
    /**
     * Obtiene la sala exclusiva de esta función.
     * <p>
     * Al recuperar este objeto, se obtiene acceso a la lista de {@link Asiento}
     * con su estado de ocupación específico para este horario.
     * @return Objeto Sala.
     */
    public Sala getSala() { return sala; }
    
    /**
     * Obtiene el horario de inicio.
     * @return Objeto LocalDateTime.
     */
    public LocalDateTime getHorario() { return horario; }

    /**
     * Actualiza el horario de la función.
     * <p>
     * <b>Nota de Diseño:</b> Actualmente, cambiar el horario no regenera automáticamente
     * el {@code idFuncion}. Si la integridad del ID es crítica respecto al horario,
     * se recomienda recrear el objeto Funcion en lugar de modificarlo.
     *
     * @param horario Nuevo LocalDateTime de inicio.
     */
    public void setHorario(LocalDateTime horario) { 
        this.horario = horario; 
        // Nota técnica: Se mantiene el ID original por consistencia referencial, 
        // aunque semánticamente el ID contenga la hora antigua.
    }

    /**
     * Representación amigable en cadena para interfaces de usuario y logs.
     * Formatea la fecha y hora para lectura humana (dd/MM/yyyy HH:mm).
     *
     * @return Cadena descriptiva: "Función [ID] - Título en Sala a las Hora".
     */
    @Override
    public String toString() {
        DateTimeFormatter printable = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("Función [%s] - %s en %s a las %s", 
                idFuncion, pelicula.getTitulo(), sala.getNombre(), horario.format(printable));
    }
}
