import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MapaParadas {

    private static final Map<String, double[]> paradas = new HashMap<>();

    public static void cargarDesdeCSV(String rutaCsv) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCsv))) {
            String linea;
            br.readLine(); // saltar cabecera
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",", 4);
                if (partes.length == 4) {
                    String nombre = partes[0];
                    double lat = Double.parseDouble(partes[1]);
                    double lon = Double.parseDouble(partes[2]);
                    paradas.put(nombre, new double[]{lat, lon});
                }
            }
            System.out.println("Paradas cargadas: " + paradas.size());
        } catch (IOException e) {
            System.out.println("Error al cargar paradas: " + e.getMessage());
        }
    }

    public static String obtenerNombreParada(double lat, double lon) {
        String nombreCercano = "Parada desconocida";
        double distanciaMin = Double.MAX_VALUE;

        for (Map.Entry<String, double[]> entry : paradas.entrySet()) {
            double[] coords = entry.getValue();
            double distancia = distancia(lat, lon, coords[0], coords[1]);

            if (distancia < 0.0005 && distancia < distanciaMin) {
                distanciaMin = distancia;
                nombreCercano = entry.getKey();
            }
        }

        return nombreCercano;
    }

    public static double distancia(double lat1, double lon1, double lat2, double lon2) {
        return AnalizadorGPS.calcularDistanciaMetros(lat1, lon1, lat2, lon2);
    }


    public static double[] getCoordenadas(String nombre) {
        return paradas.getOrDefault(nombre, null);
    }


    public static Set<String> getNombresParadas() {
        return paradas.keySet();
    }

    public static List<Parada> getTodasParadas() {
        List<Parada> resultado = new ArrayList<>();
        for (Map.Entry<String, double[]> entry : paradas.entrySet()) {
            double[] coords = entry.getValue();
            resultado.add(new Parada(entry.getKey(), coords[0], coords[1]));
        }
        return resultado;
    }
}
