import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;




public class AnalizadorGPS {


    //calcula la velocidad media ignorando la velocidad 0 (paradas)
    public static double calcularVelocidadMedia(List<GPSData> datos) {
        double suma = 0;
        int contador = 0;

        for (GPSData d : datos) {
            if (d.getSpeed() > 0) { //filtrar las paradas
                suma += d.getSpeed();
                contador++;
            }
        }
        if (contador == 0) return 0;
        return suma / contador;

    }

    public static List<GPSData> detectarParadas(List<GPSData> datos) {
        List<GPSData> paradas = new ArrayList<>();

        for (GPSData d : datos) {
            if (Math.abs(d.getSpeed()) < 0.01) {
                for (Parada p : MapaParadas.getTodasParadas()) {
                    double distancia = calcularDistanciaMetros(d.getLatitude(), d.getLongitude(), p.getLatitud(), p.getLongitud());
                    if (distancia < 30) { // margen de 30 metros
                        paradas.add(d);
                        break; // solo hace falta encontrar una parada cercana
                    }
                }
            }
        }

        return paradas;
    }

    public static int contarParadasPorRuta(List<GPSData> datos, Ruta ruta) {
        int contador = 0;
        for (GPSData d : datos) {
            if (d.getSpeed() == 0) {
                for (Parada p : ruta.getParadas()) {
                    double dist = MapaParadas.distancia(d.getLatitude(), d.getLongitude(), p.getLatitud(), p.getLongitud());
                    if (dist < 0.0005) {
                        contador++;
                        break;
                    }
                }
            }
        }
        return contador;
    }

    //Calcula la distancia real entre dos coordanadas por ChatGPT
    public static double calcularDistanciaMetros(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371000; // radio de la Tierra en metros
        double latRad1 = Math.toRadians(lat1);
        double latRad2 = Math.toRadians(lat2);
        double deltaLat = Math.toRadians(lat2 - lat1);
        double deltaLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(latRad1) * Math.cos(latRad2)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
    public static List<GPSData> filtrarPorRango(List<GPSData> lista, LocalDateTime inicio, LocalDateTime fin) {
        List<GPSData> resultado = new ArrayList<>();
        for (GPSData d : lista) {
            if (d.getTimestamp() != null &&
                    !d.getTimestamp().isBefore(inicio) &&
                    !d.getTimestamp().isAfter(fin)) {
                resultado.add(d);
            }
        }
        return resultado;
    }




}
