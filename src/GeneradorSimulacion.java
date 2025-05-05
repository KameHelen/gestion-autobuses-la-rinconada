import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class GeneradorSimulacion {

    private static final Random random = new Random();

    public static void generarSimulacionIdaYVuelta(Ruta rutaIda, Ruta rutaVuelta, List<String> busIds, LocalDateTime horaInicio, String archivoSalida) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoSalida))) {
            pw.println("busId,latitude,longitude,timestamp,speed");

            for (int i = 0; i < busIds.size(); i++) {
                String busId = busIds.get(i);
                LocalDateTime salida = horaInicio.plusMinutes(i * 30);
                LocalDateTime tiempoActual = salida;

                // Ruta de ida
                for (int j = 0; j < rutaIda.getParadas().size(); j++) {
                    Parada p = rutaIda.getParadas().get(j);
                    double speed = (j % 2 == 0) ? velocidadAleatoria() : 0.0;
                    pw.println(busId + "," + p.getLatitud() + "," + p.getLongitud() + "," + tiempoActual + "," + speed);
                    tiempoActual = tiempoActual.plusMinutes(1);
                }

                // Ruta de vuelta
                for (int j = 0; j < rutaVuelta.getParadas().size(); j++) {
                    Parada p = rutaVuelta.getParadas().get(j);
                    double speed = (j % 2 == 0) ? velocidadAleatoria() : 0.0;
                    pw.println(busId + "," + p.getLatitud() + "," + p.getLongitud() + "," + tiempoActual + "," + speed);
                    tiempoActual = tiempoActual.plusMinutes(1);
                }
            }

            System.out.println("Datos simulados con velocidades realistas generados en: " + archivoSalida);

        } catch (Exception e) {
            System.out.println("Error generando datos simulados: " + e.getMessage());
        }
    }

    private static double velocidadAleatoria() {
        return 15 + (random.nextDouble() * 15); // Velocidad entre 15 y 30 km/h
    }
}
