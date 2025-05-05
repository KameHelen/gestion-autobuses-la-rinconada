import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Archivador {

    public static void archivarCSVAntiguos(String carpetaOrigen) {
        File origen = new File(carpetaOrigen);
        File destino = new File(carpetaOrigen + "/archivados");

        if (!destino.exists()) {
            destino.mkdirs(); // Crear carpeta archivados si no existe
        }

        File[] archivos = origen.listFiles((dir, name) -> name.endsWith(".csv"));
        int archivados = 0, ignorados = 0;

        if (archivos != null) {
            for (File archivo : archivos) {
                try {
                    Instant modificado = Files.getLastModifiedTime(archivo.toPath()).toInstant();
                    Instant hace7dias = Instant.now().minus(7, ChronoUnit.DAYS);

                    if (modificado.isBefore(hace7dias)) {
                        Path destinoArchivo = Paths.get(destino.getPath(), archivo.getName());
                        Files.move(archivo.toPath(), destinoArchivo, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Archivo archivado: " + archivo.getName());
                        archivados++;
                    } else {
                        ignorados++;
                    }

                } catch (IOException e) {
                    System.out.println("Error al archivar: " + archivo.getName());
                }
            }
        }

        System.out.println("\nResumen de archivado:");
        System.out.println("Archivos archivados: " + archivados);
        System.out.println("Archivos ignorados (menos de 7 días): " + ignorados);
    }
}
