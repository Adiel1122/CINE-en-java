package mx.unam.fi.cine.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa la abstracción física de una sala de proyección dentro del modelo <b>CineByt</b>.
 * <p>
 * Esta clase actúa como un contenedor lógico y estructural para los objetos {@link Asiento}.
 * Su responsabilidad principal es definir la topología (layout) de las butacas disponible
 * según el tipo de sala ("Sala A", "Sala B", "Sala VIP").
 * </p>
 * <b>Relación con la Arquitectura:</b>
 * <ul>
 * <li>Es una entidad del <b>Modelo</b> que implementa {@link Serializable} para persistencia.</li>
 * <li>Mantiene una relación de <b>Composición</b> con {@link Asiento} (si la sala deja de existir, sus asientos también).</li>
 * <li>Es utilizada por la clase {@link Funcion} para gestionar la ocupación. Nota: Cada Función crea
 * una copia o instancia propia de Sala para manejar sus propios estados de "Ocupado/Libre" independientemente.</li>
 * </ul>
 *
 * @author Equipo CineByt
 * @version 1.0
 * @see mx.unam.fi.cine.modelo.Asiento
 * @see mx.unam.fi.cine.modelo.Funcion
 */
public class Sala implements Serializable {

    /**
     * Identificador de versión para la serialización.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único y descriptivo de la sala.
     * Determina la configuración de asientos a generar (ej. "Sala A", "Sala VIP").
     */
    private String nombre;

    /**
     * Lista dinámica que almacena la totalidad de los asientos que componen la sala.
     * Se utiliza {@code ArrayList} para permitir acceso rápido e iteración eficiente.
     */
    private List<Asiento> asientos;

    /**
     * Constructor principal de la Sala.
     * Inicializa la lista de asientos y dispara inmediatamente la configuración
     * de la topología interna mediante {@link #configurarAsientos()}.
     *
     * @param nombre Identificador de la sala (ej. "Sala A"). Debe coincidir con los casos
     * esperados en la lógica de configuración para evitar una sala vacía.
     */
    public Sala(String nombre) {
        this.nombre = nombre;
        this.asientos = new ArrayList<>();
        configurarAsientos();
    }

    /**
     * Genera y estructura la matriz de asientos según las reglas de negocio del proyecto.
     * <p>
     * Este método contiene la lógica de definición de espacios ("Hardcoded Layouts"):
     * <ul>
     * <li><b>Sala A:</b> Sala Estándar Grande. 10 filas (A-J) con 15 asientos cada una.</li>
     * <li><b>Sala B:</b> Sala Mixta/Media.
     * <ul>
     * <li>Sección Frontal: 4 filas (A-D) de 7 asientos (posiblemente para mejor visión).</li>
     * <li>Sección Trasera: 6 filas (E-J) de 15 asientos.</li>
     * </ul>
     * </li>
     * <li><b>Sala VIP:</b> Sala Exclusiva. 8 filas (A-H) con solo 6 asientos anchos.</li>
     * </ul>
     * Si el nombre no coincide, se imprime una advertencia en consola y la sala queda vacía.
     */
    private void configurarAsientos() {
        // Limpiamos la lista por seguridad para evitar duplicados si se re-inicializa
        asientos.clear();

        switch (this.nombre) {
            case "Sala A":
                // Filas A-J (10 filas), 15 asientos c/u
                generarFilas('A', 'J', 15);
                break;
            case "Sala B":
                // Lógica asimétrica para Sala B
                // Filas A-D (4 filas), 7 lugares
                generarFilas('A', 'D', 7);
                // Filas E-J (6 filas), 15 lugares
                generarFilas('E', 'J', 15);
                break;
            case "Sala VIP":
                // Filas A-H (8 filas), 6 lugares
                // Nota: Los pasillos son visuales en la GUI, lógicamente son 6 asientos consecutivos en el modelo.
                generarFilas('A', 'H', 6);
                break;
            default:
                // Manejo de error o caso por defecto para nombres desconocidos
                System.out.println("ADVERTENCIA: Tipo de sala '" + this.nombre + "' no reconocido. Se crea instancia vacía.");
        }
    }

    /**
     * Método auxiliar ("Helper") para instanciar y agregar un rango secuencial de filas a la sala.
     * Itera sobre los caracteres de las filas y los números de los asientos.
     *
     * @param filaInicio     Carácter de la fila inicial (ej. 'A').
     * @param filaFin        Carácter de la fila final (ej. 'J'). Inclusive.
     * @param cantidadAsientos Número de asientos a generar por cada fila.
     */
    private void generarFilas(char filaInicio, char filaFin, int cantidadAsientos) {
        for (char f = filaInicio; f <= filaFin; f++) {
            for (int i = 1; i <= cantidadAsientos; i++) {
                asientos.add(new Asiento(f, i));
            }
        }
    }

    /**
     * Obtiene el nombre identificador de la sala.
     * @return El nombre de la sala.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la lista completa de asientos de la sala.
     * @return Referencia a la lista de objetos {@link Asiento}.
     */
    public List<Asiento> getAsientos() {
        return asientos;
    }

    /**
     * Busca una instancia específica de asiento dentro de la colección de la sala.
     * <p>
     * Realiza una búsqueda lineal comparando la fila y el número.
     * Es fundamental para el proceso de compra de boletos, donde se necesita
     * cambiar el estado de un asiento específico.
     *
     * @param fila   Letra de la fila del asiento buscado.
     * @param numero Número del asiento buscado.
     * @return El objeto {@link Asiento} encontrado, o {@code null} si las coordenadas no existen en esta sala.
     */
    public Asiento buscarAsiento(char fila, int numero) {
        for (Asiento a : asientos) {
            if (a.getFila() == fila && a.getNumero() == numero) {
                return a;
            }
        }
        return null;
    }

    /**
     * Representación en cadena de la Sala.
     * Útil para logs y depuración rápida del estado de capacidad.
     *
     * @return Cadena con el nombre y la capacidad total de personas.
     */
    @Override
    public String toString() {
        return "Sala: " + nombre + " (Capacidad: " + asientos.size() + " personas)";
    }
}
