package comun;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class GeneradorTxt {

    private String nombreArchivo;
    private int cantidadProcesos;
    private int s; // Tiempo minimo
    private int m; // Tiempo maximo
    private int minSize;
    private int maxSize;

    public GeneradorTxt(String nombreArchivo, int cantidadProcesos, int tiempoMin, int tiempoMax, int minSize,
            int maxSize) {
        this.nombreArchivo = nombreArchivo;
        this.cantidadProcesos = cantidadProcesos;
        this.s = tiempoMin;
        this.m = tiempoMax;
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    // Metodo que genera el archivo de procesos (lo guarda en una directorio si se
    // le especifica una ruta)
    public void generarArchivo() {

        Random random = new Random();

        File archivo = new File(this.nombreArchivo);
        File directorio = archivo.getParentFile(); // Obtener la carpeta de la ruta

        // Si la ruta incluye un directorio y ese directorio aun no existe, lo crea
        if (directorio != null && !directorio.exists()) {
            directorio.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(this.nombreArchivo))) {

            // %-16s significa un string alineado a la izquierda ocupando 15 caracteres
            // %n es el salto de linea independiente del sistema operativo
            String encabezado = "%-16s %-16s %-16s%n";

            String datos = "%-16d %-16d %-16d%n";

            // Escribir el encabezado de la tabla
            writer.printf(encabezado, "proceso (id)", "tiempo (seg)", "Size (kb)");

            // Generar los datos de cada proceso
            for (int id = 1; id <= this.cantidadProcesos; id++) {
                int tiempo = random.nextInt((this.m - this.s) + 1) + this.s;
                int tam = random.nextInt((this.maxSize - this.minSize) + 1) + this.minSize;

                // Escribir la fila de datos
                writer.printf(datos, id, tiempo, tam);
            }

            System.out.println("Archivo generado en: " + archivo.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error durante la generacion del archivo: " + e.getMessage());
        }
    }
}