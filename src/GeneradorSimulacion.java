import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

public class GeneradorSimulacion {

    public static void generarSimulacionIdaYVuelta(Ruta rutaIda, Ruta rutaVuelta, List<String> busIds, LocalDateTime horaInicio, String archivoSalida) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoSalida))) {
            pw.println("busId,latitude,longitude,timestamp,speed");

            for (int i = 0; i < busIds.size(); i++) {
                String busId = busIds.get(i);

                // Hora de salida del bus (escalonada)
                LocalDateTime salida = horaInicio.plusMinutes(i * 30);

                // 1. Recorrer ruta de ida
                LocalDateTime tiempoActual = salida;
                for (int j = 0; j < rutaIda.getParadas().size(); j++) {
                    Parada p = rutaIda.getParadas().get(j);
                    double speed = (j % 2 == 0) ? 25.0 : 0.0;
                    pw.println(busId + "," + p.getLatitud() + "," + p.getLongitud() + "," + tiempoActual + "," + speed);
                    tiempoActual = tiempoActual.plusMinutes(1);
                }

                // 2. Recorrer ruta de vuelta justo después
                for (int j = 0; j < rutaVuelta.getParadas().size(); j++) {
                    Parada p = rutaVuelta.getParadas().get(j);
                    double speed = (j % 2 == 0) ? 25.0 : 0.0;
                    pw.println(busId + "," + p.getLatitud() + "," + p.getLongitud() + "," + tiempoActual + "," + speed);
                    tiempoActual = tiempoActual.plusMinutes(1);
                }
            }

            System.out.println("Datos simulados de ida y vuelta generados en: " + archivoSalida);

        } catch (Exception e) {
            System.out.println("Error generando datos simulados: " + e.getMessage());
        }
    }
}
