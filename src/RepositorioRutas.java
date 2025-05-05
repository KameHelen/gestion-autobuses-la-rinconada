import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RepositorioRutas {

    private static Ruta rutaIda;
    private static Ruta rutaVuelta;

    static {
        rutaIda = new Ruta("Línea 1 - Ida");
        rutaVuelta = new Ruta("Línea 1 - Vuelta");
        cargarParadasDesdeCSV("src/datos/paradas.csv"); // ajusta ruta si es distinta
    }

    private static void cargarParadasDesdeCSV(String rutaCsv) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCsv))) {
            String linea;
            br.readLine(); // Saltar cabecera
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",", 4);
                if (partes.length == 4) {
                    String nombre = partes[0];
                    double lat = Double.parseDouble(partes[1]);
                    double lon = Double.parseDouble(partes[2]);
                    String direccion = partes[3].trim().toLowerCase();

                    Parada parada = new Parada(nombre, lat, lon);
                    if (direccion.equals("ida")) {
                        rutaIda.agregarParada(parada);
                    } else if (direccion.equals("vuelta")) {
                        rutaVuelta.agregarParada(parada);
                    }
                }
            }
            System.out.println("✔ Paradas de ida: " + rutaIda.getParadas().size());
            System.out.println("✔ Paradas de vuelta: " + rutaVuelta.getParadas().size());
        } catch (IOException e) {
            System.out.println("Error al cargar rutas desde CSV: " + e.getMessage());
        }
    }

    public static Ruta getRutaIda() {
        return rutaIda;
    }

    public static Ruta getRutaVuelta() {
        return rutaVuelta;
    }
}
