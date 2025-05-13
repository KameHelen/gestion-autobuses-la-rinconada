import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.google.gson.Gson; // Añadido gson-2.10.1.jar manualmente al proyecto

/*
  Esta clase se encarga de exportar la última posición conocida de cada autobús
  en formato JSON, utilizando la librería Gson (añadida manualmente al proyecto).
 */
public class ExportadorJSON {


    public static void exportarUltimaPosicion(List<GPSData> datos) {
        Map<String, GPSData> ultimaPosicionPorBus = new HashMap<>();

        for (GPSData d : datos) {
            String busId = d.getBusId();

            if (!ultimaPosicionPorBus.containsKey(busId) ||
                    d.getTimestamp().isAfter(ultimaPosicionPorBus.get(busId).getTimestamp())) {
                ultimaPosicionPorBus.put(busId, d);
            }
        }

        Gson gson = new Gson();

        for (String busId : ultimaPosicionPorBus.keySet()) {
            GPSData ultima = ultimaPosicionPorBus.get(busId);

            System.out.println("Exportando JSON para " + busId + " -> " + ultima.getTimestamp());

            Map<String, Object> jsonMap = new LinkedHashMap<>();
            jsonMap.put("busId", ultima.getBusId());
            jsonMap.put("latitude", ultima.getLatitude());
            jsonMap.put("longitude", ultima.getLongitude());
            jsonMap.put("timestamp", ultima.getTimestamp().toString());

            try (FileWriter writer = new FileWriter(busId.toLowerCase() + "_status.json")) {
                gson.toJson(jsonMap, writer);
                System.out.println("Archivo generado: " + busId.toLowerCase() + "_status.json");
            } catch (IOException e) {
                System.out.println("Error al escribir JSON para " + busId + ": " + e.getMessage());
            }
        }
    }


    public static void exportarModificado(GPSData data) {
        Gson gson = new Gson();

        Map<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put("busId", data.getBusId());
        jsonMap.put("latitude", data.getLatitude());
        jsonMap.put("longitude", data.getLongitude());
        jsonMap.put("timestamp", data.getTimestamp().toString());

        String nombreArchivo = data.getBusId().toLowerCase() + "_status_modificado.json";

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            gson.toJson(jsonMap, writer);
            System.out.println("Archivo modificado generado: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al escribir JSON modificado: " + e.getMessage());
        }
    }

}

