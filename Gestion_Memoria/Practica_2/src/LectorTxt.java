import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorTxt {

    private String rutaArchivo;

    public LectorTxt(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    // Metodo que lee el archivo de procesos y retorna la lista de objetos Proceso
    public List<Proceso> cargarProcesos() {
        List<Proceso> listaProcesos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.rutaArchivo))) {
            
            // Leer la primera linea y descartarla (pues son los encabezados)
            String linea = br.readLine();

            // Leer el resto del archivo
            while ((linea = br.readLine()) != null) {
                // Limpiar espacios en blanco al inicio y final de la linea
                linea = linea.trim();
                
                // Ignorar lineas vacias
                if (linea.isEmpty()) {
                    continue;
                }

                // Separar los valores ("\\s+" divide la cadena cada vez que encuentra uno o mas espacios en blanco consecutivos)
                String[] datos = linea.split("\\s+");

                
                if (datos.length >= 3) { // Validar que la linea tenga exactamente los 3 datos esperados
                    int id = Integer.parseInt(datos[0]);
                    int tiempo = Integer.parseInt(datos[1]);
                    int size = Integer.parseInt(datos[2]);

                    Proceso nuevoProceso = new Proceso(id, tiempo, size);
                    listaProcesos.add(nuevoProceso);
                }
            }
        } catch (IOException e) {
            System.err.println("Error de lectura en el archivo: " + e.getMessage());
        } 

        return listaProcesos;
    }
}