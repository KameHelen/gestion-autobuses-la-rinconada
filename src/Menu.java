import com.google.gson.Gson;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.*;

public class Menu {

    public static void mostrar(List<GPSData> datos) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n---- MENÚ DE CONSULTA ----");
            System.out.println("1. Ver velocidad media de un bus");
            System.out.println("2. Ver paradas (dónde se ha detenido un bus)");
            System.out.println("3. Ver última posición de un bus");
            System.out.println("4. Modificar coordenadas de un registro (simular cambio de ruta)");
            System.out.println("5. Ver todas las paradas y consultar próximas llegadas");
            System.out.println("6. Filtrar datos por bus y rango horario");
            System.out.println("0. Salir");

            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    // Velocidad media de un autobús
                    System.out.print("Introduce ID del bus (BUS01, BUS02, BUS03): ");
                    String bus1 = sc.nextLine();
                    List<GPSData> datosBus1 = CSVProcessor.filtrarPorBus(datos, bus1);
                    double media = AnalizadorGPS.calcularVelocidadMedia(datosBus1);
                    System.out.println("Velocidad media de " + bus1 + ": " + media + " km/h");
                    LocalDateTime ultimo = CSVProcessor.obtenerUltimoTimestamp(datosBus1);
                    System.out.println("Último registro de " + bus1 + ": " + ultimo);
                    break;

                case 2:
                    System.out.print("Introduce ID del bus (BUS01, BUS02, BUS03): ");
                    String bus2 = sc.nextLine();
                    List<GPSData> datosBus2 = CSVProcessor.filtrarPorBus(datos, bus2);

                    System.out.println("Total registros encontrados: " + datosBus2.size());
                    for (GPSData d : datosBus2) {
                        System.out.println(d.getTimestamp() + " | velocidad: " + d.getSpeed());
                    }

                    List<GPSData> paradas = AnalizadorGPS.detectarParadas(datosBus2);
                    System.out.println("Paradas de " + bus2 + ": " + paradas.size());

                    for (GPSData p : paradas) {
                        String nombreParada = MapaParadas.obtenerNombreParada(p.getLatitude(), p.getLongitude());
                        System.out.println("Parada en " + nombreParada + " (" + p.getLatitude() + ", " + p.getLongitude() + ") a las " + p.getTimestamp());
                    }

                    int paradasIda = AnalizadorGPS.contarParadasPorRuta(datosBus2, RepositorioRutas.getRutaIda());
                    int paradasVuelta = AnalizadorGPS.contarParadasPorRuta(datosBus2, RepositorioRutas.getRutaVuelta());

                    System.out.println("\nParadas detectadas por ruta:");
                    System.out.println(" - Ruta Ida: " + paradasIda);
                    System.out.println(" - Ruta Vuelta: " + paradasVuelta);
                    break;

                case 3:
                    System.out.print("Introduce ID del bus (BUS01, BUS02, BUS03): ");
                    String bus3 = sc.nextLine().toLowerCase();
                    String jsonPath = bus3 + "_status.json";

                    try (FileReader reader = new FileReader(jsonPath)) {
                        Gson gson = new Gson();
                        Map<?, ?> datosJson = gson.fromJson(reader, Map.class);
                        System.out.println("Última posición de " + bus3.toUpperCase() + ":");
                        System.out.println(datosJson);

                        if (datosJson.containsKey("latitude") && datosJson.containsKey("longitude")) {
                            double lat = ((Number) datosJson.get("latitude")).doubleValue();
                            double lon = ((Number) datosJson.get("longitude")).doubleValue();
                            String nombreParada = MapaParadas.obtenerNombreParada(lat, lon);
                            System.out.println("Ubicación aproximada: " + nombreParada);
                        }
                    } catch (Exception e) {
                        System.out.println("Error leyendo el archivo JSON: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Introduce ID del bus (BUS01, BUS02, BUS03): ");
                    String busIdEdit = sc.nextLine();

                    System.out.print("Introduce timestamp exacto (ej: 2025-04-09T08:15:00): ");
                    String fechaStr = sc.nextLine();

                    System.out.print("Nueva latitud: ");
                    double nuevaLat = sc.nextDouble();

                    System.out.print("Nueva longitud: ");
                    double nuevaLon = sc.nextDouble();
                    sc.nextLine();

                    try {
                        LocalDateTime ts = LocalDateTime.parse(fechaStr);
                        boolean modificadoOK = EditorGPS.modificarCoordenadas(datos, busIdEdit, ts, nuevaLat, nuevaLon);

                        if (modificadoOK) {
                            System.out.println("Registro modificado correctamente.");
                            GPSData modificado = null;
                            for (GPSData d : datos) {
                                if (d.getBusId().equalsIgnoreCase(busIdEdit) && d.getTimestamp().equals(ts)) {
                                    modificado = d;
                                    break;
                                }
                            }
                            if (modificado != null) {
                                ExportadorJSON.exportarModificado(modificado);
                            }
                        } else {
                            System.out.println("No se encontró el registro especificado.");
                        }
                    } catch (Exception e) {
                        System.out.println("Formato de fecha inválido.");
                    }
                    break;

                case 5:
                    consultarProximasLlegadas(datos, sc);
                    break;

                case 6:
                    System.out.print("Introduce ID del bus (ej: BUS01): ");
                    String busFiltro = sc.nextLine();
                    System.out.print("Introduce fecha inicio (ej: 2025-04-09T08:00): ");
                    String inicioStr = sc.nextLine();
                    System.out.print("Introduce fecha fin (ej: 2025-04-09T09:00): ");
                    String finStr = sc.nextLine();

                    try {
                        LocalDateTime inicio = LocalDateTime.parse(inicioStr);
                        LocalDateTime fin = LocalDateTime.parse(finStr);
                        List<GPSData> porBus = CSVProcessor.filtrarPorBus(datos, busFiltro);
                        List<GPSData> filtrado = AnalizadorGPS.filtrarPorRango(porBus, inicio, fin);
                        System.out.println("Registros encontrados: " + filtrado.size());
                        for (GPSData d : filtrado) {
                            System.out.printf("[%s] lat=%.6f, lon=%.6f, vel=%.1f km/h\n",
                                    d.getTimestamp(), d.getLatitude(), d.getLongitude(), d.getSpeed());
                        }
                    } catch (Exception e) {
                        System.out.println("Error: formato incorrecto. Usa yyyy-MM-ddTHH:mm");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }

    private static void consultarProximasLlegadas(List<GPSData> datos, Scanner sc) {
        System.out.println("\nConsultar próximas llegadas");
        System.out.println("1. Línea 1 - Ida");
        System.out.println("2. Línea 1 - Vuelta");
        System.out.print("Selecciona una dirección: ");
        int dir = sc.nextInt();
        sc.nextLine();

        Ruta rutaSeleccionada;
        if (dir == 1) {
            rutaSeleccionada = RepositorioRutas.getRutaIda();
        } else if (dir == 2) {
            rutaSeleccionada = RepositorioRutas.getRutaVuelta();
        } else {
            System.out.println("Dirección inválida.");
            return;
        }

        List<Parada> paradasRuta = rutaSeleccionada.getParadas();

        System.out.println("\n--- PARADAS DE " + rutaSeleccionada.getNombre() + " ---");
        for (int i = 0; i < paradasRuta.size(); i++) {
            System.out.println((i + 1) + ". " + paradasRuta.get(i).getNombre());
        }

        System.out.print("Selecciona una parada (número): ");
        int seleccion = sc.nextInt();
        sc.nextLine();

        if (seleccion < 1 || seleccion > paradasRuta.size()) {
            System.out.println("Parada inválida.");
            return;
        }

        Parada paradaElegida = paradasRuta.get(seleccion - 1);
        double[] coords = {paradaElegida.getLatitud(), paradaElegida.getLongitud()};
        LocalDateTime ahora = SimuladorTiempo.ahora();

        Map<String, GPSData> proximosPorBus = new HashMap<>();

        for (GPSData d : datos) {
            double distancia = AnalizadorGPS.calcularDistanciaMetros(d.getLatitude(), d.getLongitude(), coords[0], coords[1]);
            boolean futuro = d.getTimestamp().isEqual(ahora) || d.getTimestamp().isAfter(ahora);
            if (futuro && distancia < 0.0005) {
                proximosPorBus.putIfAbsent(d.getBusId(), d);
            }
        }

        if (proximosPorBus.isEmpty()) {
            System.out.println("No hay buses próximos para esa parada.");
        } else {
            List<GPSData> llegadasOrdenadas = new ArrayList<>(proximosPorBus.values());
            llegadasOrdenadas.sort(Comparator.comparing(GPSData::getTimestamp));
            for (GPSData d : llegadasOrdenadas) {
                long minutos = java.time.Duration.between(ahora, d.getTimestamp()).toMinutes();
                System.out.println("El bus " + d.getBusId() + " llegará a " + paradaElegida.getNombre() + " en " + minutos + " minutos (a las " + d.getTimestamp() + ")");
            }
        }
    }
}