package mx.unam.fi.cine.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una oferta comercial compuesta por múltiples artículos en la dulcería de <b>CineByt</b>.
 * <p>
 * Esta clase implementa una estructura de agregación que permite agrupar varias instancias de {@link Producto}
 * bajo un solo precio promocional. Su objetivo principal es incentivar la venta mediante descuentos automáticos.
 * </p>
 * <b>Reglas de Negocio:</b>
 * <ul>
 * <li><b>Composición:</b> Un combo contiene una lista dinámica de productos.</li>
 * <li><b>Política de Precios:</b> El precio del combo NO es fijo; se calcula dinámicamente sumando
 * los precios unitarios de sus componentes y aplicando un descuento porcentual estandarizado.</li>
 * </ul>
 *
 * @author Equipo CineByt
 * @version 1.0
 * @see mx.unam.fi.cine.modelo.Producto
 * @see mx.unam.fi.cine.controlador.ControladorDulceria
 */
public class Combo implements Serializable {

    /**
     * Identificador de versión para la serialización.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Nombre comercial del paquete (ej. "Combo Pareja", "Combo Amix").
     */
    private String nombre;

    /**
     * Lista que almacena los productos individuales que conforman el paquete.
     * Utiliza {@link ArrayList} para permitir una composición dinámica de n elementos.
     */
    private List<Producto> productos;

    /**
     * Constructor para crear un nuevo Combo vacío.
     * <p>
     * Inicializa la lista interna de productos, preparándola para recibir ítems mediante {@link #agregarProducto(Producto)}.
     *
     * @param nombre Nombre comercial del combo.
     */
    public Combo(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    /**
     * Agrega un artículo individual a la composición del combo.
     * <p>
     * Este método permite construir el paquete paso a paso.
     *
     * @param p El objeto {@link Producto} a añadir a la lista.
     */
    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    /**
     * Ejecuta la lógica financiera para determinar el costo final del combo al cliente.
     * <p>
     * <b>Algoritmo de Precio:</b>
     * <ol>
     * <li>Itera sobre la lista de productos y suma sus precios unitarios base.</li>
     * <li>Aplica un <b>descuento fijo del 10%</b> sobre el total acumulado (factor 0.90).</li>
     * </ol>
     *
     * @return El precio final calculado con descuento incluido.
     */
    public double calcularPrecioTotal() {
        double suma = 0;
        for (Producto p : productos) {
            suma += p.getPrecio();
        }
        // Regla de Negocio: Se aplica un 10% de descuento sobre la suma de los componentes
        return suma * 0.90; 
    }

    /**
     * Obtiene el nombre del combo.
     * @return Cadena con el nombre.
     */
    public String getNombre() { return nombre; }

    /**
     * Representación textual del Combo para el ticket o menú.
     * <p>
     * Resume el contenido mostrando el nombre, la cantidad de ítems incluidos
     * y el precio final ya calculado.
     *
     * @return Cadena formato: "Nombre (Incluye N productos) - Precio Final: $X.XX".
     */
    @Override
    public String toString() {
        return String.format("%s (Incluye %d productos) - Precio Final: $%.2f", 
                nombre, productos.size(), calcularPrecioTotal());
    }
}
