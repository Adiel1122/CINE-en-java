package mx.unam.fi.cine.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria para manejar la persistencia de datos (Serialización y Texto).
 * Se encarga de guardar y leer objetos y archivos de texto plano en la carpeta 'ArchivosAplicacion'.
 */
public class GestorArchivos{
    // Constante para la carpeta raíz de archivos 
    public static final String CARPETA_ARCHIVOS = "ArchivosAplicacion";

    /**
     * Bloque estático para asegurar que la carpeta exista al iniciar la clase.
     */
    static {
        File directorio = new File(CARPETA_ARCHIVOS);
        if (!directorio.exists()) {
            if (directorio.mkdir()) {
                System.out.println("Carpeta '" + CARPETA_ARCHIVOS + "' creada exitosamente.");
            }
        }
    }

    // ==========================================
    // MÉTODOS PARA SERIALIZACIÓN (Objetos .dat)
    // ==========================================

    /**
     * Guarda un objeto (o una lista de objetos) en un archivo binario (.dat).
     * Útil para guardar la lista de Usuarios y Funciones.
     *
     * @param nombreArchivo Nombre del archivo (ej: "usuarios.dat").
     * @param objeto Objeto a serializar (debe implementar Serializable).
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public static void guardarObjeto(String nombreArchivo, Object objeto) throws IOException {
        String rutaCompleta = CARPETA_ARCHIVOS + File.separator + nombreArchivo;
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaCompleta))) {
            oos.writeObject(objeto);
        }
    }

    /**
     * Lee un objeto desde un archivo binario (.dat).
     *
     * @param nombreArchivo Nombre del archivo a leer.
     * @return El objeto leído (se debe hacer cast al tipo esperado).
     * @throws IOException Si hay error de lectura o el archivo no existe.
     * @throws ClassNotFoundException Si la clase del objeto no se encuentra.
     */
    public static Object leerObjeto(String nombreArchivo) throws IOException, ClassNotFoundException {
        String rutaCompleta = CARPETA_ARCHIVOS + File.separator + nombreArchivo;
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaCompleta))) {
            return ois.readObject();
        }
    }

    // ==========================================
    // MÉTODOS PARA TEXTO PLANO (.txt)
    // ==========================================

    /**
     * Escribe texto en un archivo.
     * Útil para notificaciones, tickets y reportes[cite: 154, 163].
     *
     * @param nombreArchivo Nombre del archivo (ej: "precios.txt").
     * @param contenido Texto a guardar.
     * @param append Si es true, agrega el texto al final. Si es false, sobrescribe el archivo.
     * @throws IOException Si ocurre un error de escritura.
     */
    public static void escribirTexto(String nombreArchivo, String contenido, boolean append) throws IOException {
        String rutaCompleta = CARPETA_ARCHIVOS + File.separator + nombreArchivo;
        
        // FileWriter con append flag
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaCompleta, append))) {
            bw.write(contenido);
            bw.newLine(); // Agrega salto de línea automáticamente
        }
    }

    /**
     * Lee el contenido de un archivo de texto línea por línea.
     * Útil para leer la configuración de precios de dulcería[cite: 124].
     *
     * @param nombreArchivo Nombre del archivo a leer.
     * @return Una lista de Strings, donde cada elemento es una línea del archivo.
     * @throws IOException Si el archivo no existe o hay error de lectura.
     */
    public static List<String> leerArchivoTexto(String nombreArchivo) throws IOException {
        String rutaCompleta = CARPETA_ARCHIVOS + File.separator + nombreArchivo;
        List<String> lineas = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCompleta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
    }

    /**
     * Método auxiliar para verificar si un archivo existe.
     * Ayuda a evitar excepciones al intentar leer archivos nuevos.
     */
    public static boolean existeArchivo(String nombreArchivo) {
        File archivo = new File(CARPETA_ARCHIVOS + File.separator + nombreArchivo);
        return archivo.exists();
    }
}
