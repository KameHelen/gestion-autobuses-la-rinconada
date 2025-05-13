import java.time.LocalDateTime;
import java.util.List;

public class EditorGPS {

    // Modifica las coordenadas de un registro específico por bus y timestamp
    public static boolean modificarCoordenadas(List<GPSData> datos, String busId, LocalDateTime timestamp, double nuevaLat, double nuevaLon) {
        for (GPSData d : datos) {
            if (d.getBusId().equals(busId) && d.getTimestamp().equals(timestamp)) {
                // Creamos un nuevo objeto y lo reemplazamos
                d.setLatitude(nuevaLat);
                d.setLongitude(nuevaLon);
                return true;
            }
        }
        return false; // No se encontró el registro
    }
}
