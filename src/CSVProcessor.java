import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CSVProcessor {


/* Metodo buscado en ChatGPT y tabla generada por el

     En un entorno real, estos datos se almacenarían en una base de datos.

     Ejemplo de tabla en una base de datos relacional (MySQL o PostgreSQL):

     Tabla: gps_data
       --------------------------------------------
       | id | bus_id | latitude | longitude | timestamp           | speed |
       --------------------------------------------
       | 1  | BUS01  | 37.4859  | -5.9273   | 2025-04-09T08:00:00 | 25.0  |

     Esta estructura permite realizar consultas SQL, estadísticas y filtrados.

     Alternativamente, en una base de datos NoSQL como MongoDB o Firebase,
     los registros se guardarían como documentos JSON, por ejemplo:

     {
       "busId": "BUS01",
       "latitude": 37.4859,
       "longitude": -5.9273,
       "timestamp": "2025-04-09T08:00:00",
       "speed": 25.0
      }

      Este enfoque es más flexible, pero menos potente para análisis complejos.
     */


public static List<GPSData> cargarDesdeCSV(String rutaArchivo) {
    List<GPSData> datos = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    try (BufferedReader br = Files.newBufferedReader(Paths.get(rutaArchivo))) {
        String linea;
        br.readLine(); // saltar cabecera
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length != 5) continue; // dato incompleto

            try {
                String busId = partes[0];
                double lat = Double.parseDouble(partes[1]);
                double lon = Double.parseDouble(partes[2]);
                LocalDateTime timestamp = LocalDateTime.parse(partes[3], formatter);
                double speed = Double.parseDouble(partes[4]);

                // Validaciones básicas
                if (lat < -90 || lat > 90 || lon < -180 || lon > 180) continue;
                if (speed < 0 || speed > 150) continue;

                datos.add(new GPSData(busId, lat, lon, timestamp, speed));
            } catch (Exception e) {
                // Si hay error en la línea, la ignoramos
                System.out.println("Línea inválida: " + linea);
            }
        }
    } catch (IOException e) {
        System.out.println("Error leyendo el archivo: " + e.getMessage());
    }

    return datos;
}

// Devuelve los datos que correspondan a un autobus en concreto
public static List<GPSData> filtrarPorBus(List<GPSData> lista, String busId) {
    List<GPSData> resultado = new ArrayList<>();
    for (GPSData d : lista) {
        if (d.getBusId().equalsIgnoreCase(busId)) {
            resultado.add(d);
        }
    }
    return resultado;
}

    // Registros en un horario en concreto
    public static List<GPSData> filtrarPorRango(List<GPSData> lista, LocalDateTime inicio, LocalDateTime fin) {
        List<GPSData> resultado = new ArrayList<>();
        for (GPSData d : lista) {
            if (d.getTimestamp().isAfter(inicio) && d.getTimestamp().isBefore(fin)) {
                resultado.add(d);
            }
        }
        return resultado;
    }

    public static LocalDateTime obtenerUltimoTimestamp(List<GPSData> datos) {
        LocalDateTime max = LocalDateTime.MIN;
        for (GPSData d : datos) {
            if (d.getTimestamp().isAfter(max)) {
                max = d.getTimestamp();
            }
        }
        return max;
    }


}
