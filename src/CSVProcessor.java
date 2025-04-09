import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CSVProcessor {

//buscado en ChatGPT
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
    public static List<GPSData> filtrarPorBus(List<GPSData> lista, String busId) {
        List<GPSData> resultado = new ArrayList<>();
        for (GPSData d : lista) {
            if (d.getBusId().equals(busId)) {
                resultado.add(d);
            }
        }
        return resultado;
    }

    public static List<GPSData> filtrarPorRango(List<GPSData> lista, LocalDateTime inicio, LocalDateTime fin) {
        List<GPSData> resultado = new ArrayList<>();
        for (GPSData d : lista) {
            if (d.getTimestamp().isAfter(inicio) && d.getTimestamp().isBefore(fin)) {
                resultado.add(d);
            }
        }
        return resultado;
    }

}
