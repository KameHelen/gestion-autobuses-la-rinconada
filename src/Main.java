import java.util.List;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {

        String ruta = "src/datos/datos_gps.csv";
        List<GPSData> datos = CSVProcessor.cargarDesdeCSV(ruta);

       /* System.out.println("----- TODOS LOS DATOS -----");
        for (GPSData d : datos) {
            System.out.println(d);
        } */

        // Filtrar por autobús BUS01
        List<GPSData> bus01 = CSVProcessor.filtrarPorBus(datos, "BUS01");

        System.out.println("\n----- DATOS DE BUS01 -----");
        for (GPSData d : bus01) {
            System.out.println(d);
        }

        // Filtrar por horario: de 08:00 a 08:10
        LocalDateTime inicio = LocalDateTime.of(2025, 4, 9, 8, 0);
        LocalDateTime fin = LocalDateTime.of(2025, 4, 9, 8, 10);
        List<GPSData> rango = CSVProcessor.filtrarPorRango(datos, inicio, fin);

        System.out.println("\n----- DATOS ENTRE 08:00 Y 08:10 -----");
        for (GPSData d : rango) {
            System.out.println(d);
        }


        // Calcular velocidad media de BUS01
        double media = AnalizadorGPS.calcularVelocidadMedia(bus01);
        System.out.println("\nVelocidad media de BUS01: " + media + " km/h");


    }

}