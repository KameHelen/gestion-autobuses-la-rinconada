import java.util.*;
import java.io.FileReader;
import java.time.LocalDateTime;
import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {
        // Cargar paradas desde CSV
        MapaParadas.cargarDesdeCSV("src/datos/paradas.csv");

        // Inicializar rutas con paradas
        Ruta rutaIda = RepositorioRutas.getRutaIda();
        Ruta rutaVuelta = RepositorioRutas.getRutaVuelta();

        // Generar datos simulados
        List<String> buses = List.of("BUS01", "BUS02", "BUS03");
        LocalDateTime horaInicio = LocalDateTime.of(2025, 4, 9, 8, 0);

        GeneradorSimulacion.generarSimulacionIdaYVuelta(
                rutaIda,
                rutaVuelta,
                buses,
                horaInicio,
                "src/datos/datos_simulados.csv"
        );

        // Leer los datos recién generados
        String ruta = "src/datos/datos_simulados.csv";
        List<GPSData> datos = CSVProcessor.cargarDesdeCSV(ruta);

        // Exportar última posición de cada bus en JSON
        ExportadorJSON.exportarUltimaPosicion(datos);

        // Archivar CSV antiguos (requisito del proyecto)
        Archivador.archivarCSVAntiguos("src/datos");

        // Inicializar reloj simulado
        SimuladorTiempo.inicializar(horaInicio);

        System.out.println("Registros cargados: " + datos.size());

        // Mostrar menú principal (nuevo metodo separado)
        Menu.mostrar(datos);
    }
}
