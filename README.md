
# Sistema Integral de Seguimiento GPS en Autobuses – La Rinconada

Este proyecto simula un sistema de monitoreo de autobuses circulares en La Rinconada / San José de la Rinconada, cubriendo todas las etapas del ciclo de vida del dato: desde la generación de datos GPS hasta su análisis y distribución.

Desarrollado en **Java**, con estructura modular y enfoque didáctico.

---

## Funcionalidades principales

- Simulación de rutas reales con paradas definidas.
- Generación de datos GPS realistas cada minuto para 3 autobuses.
- Detección de paradas (velocidad 0 + cercanía a parada real).
- Cálculo de velocidad media por autobús.
- Exportación de la última posición a archivos JSON.
- Modificación de rutas (simulación de incidencias).
- Menú interactivo por consola.
- Archivado automático de CSVs antiguos (>7 días).

---

## Estructura del proyecto

- `GPSData.java`: modelo de registro GPS.
- `CSVProcessor.java`: carga y validación de datos desde CSV.
- `GeneradorSimulacion.java`: genera datos de simulación.
- `AnalizadorGPS.java`: detecta paradas y calcula estadísticas.
- `ExportadorJSON.java`: exporta últimas posiciones a JSON.
- `EditorGPS.java`: permite modificar coordenadas simuladas.
- `Archivador.java`: mueve CSVs antiguos a `/archivados/`.
- `Main.java`: contiene el menú interactivo principal.
- `MapaParadas.java`: contiene las paradas reales del municipio.
- `RepositorioRutas.java`: define rutas de ida y vuelta.
- `Ruta.java` / `Parada.java`: modelos de rutas y paradas.
- `SimuladorTiempo.java`: controla el tiempo simulado para análisis.

---

## Archivos incluidos

- `/src/datos/datos_simulados.csv` – datos GPS generados.
- `bus01_status.json`, `bus02_status.json`, ... – última posición de cada autobús.
- `README.md` – este archivo.
- `archivados/` – carpeta con archivos CSV antiguos.

---

## Cómo usar

1. Compila el proyecto Java.
2. Ejecuta `Main.java`.
3. Usa el menú para consultar información de los autobuses:
   - Ver velocidad media.
   - Detectar paradas.
   - Consultar próxima llegada a una parada.
   - Modificar coordenadas de un registro.
   - Exportar datos a JSON.

---

## Fases del ciclo de vida del dato (según el enunciado)

| Fase               | Implementación                                                                 |
|--------------------|--------------------------------------------------------------------------------|
| **Captura**        | Generación de datos GPS simulados (`GeneradorSimulacion`)                      |
| **Almacenamiento** | `ArrayList<GPSData>` + comentarios para MySQL/MongoDB                          |
| **Procesamiento**  | Validación de registros GPS (`CSVProcessor`)                                   |
| **Análisis**       | Velocidad media, detección de paradas, clasificación por ruta (`AnalizadorGPS`)|
| **Distribución**   | Exportación JSON por autobús (`ExportadorJSON`)                                |
| **Uso**            | Menú interactivo en consola (`Main.java`)                                      |
| **Mantenimiento**  | Modificación de rutas (`EditorGPS`)                                            |
| **Archivado**      | Mueve CSVs viejos a `/archivados/` (`Archivador`)                              |

---

## Requisitos

- Java 11 o superior
- Gson (`gson-2.10.1.jar`) incluido manualmente en el proyecto
