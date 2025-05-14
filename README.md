# Proyecto de Gestión de Autobuses en Tiempo Real - La Rinconada

Este proyecto simula un sistema completo de gestión y análisis de autobuses circulares en La Rinconada / San José de la Rinconada, desarrollado en Java.

---

## Objetivo del Proyecto
Desarrollar un sistema integral que aborde todas las etapas del ciclo de vida del dato usando datos GPS simulados para tres autobuses. El sistema permite capturar, almacenar, procesar, analizar, distribuir y visualizar información en tiempo real.

---

## Funcionalidades Principales

- **Carga y procesamiento de datos GPS simulados** desde un archivo CSV.
- **Generación de rutas de ida y vuelta** con coordenadas reales.
- **Detección de paradas** (velocidad 0) y cálculo de velocidad media.
- **Visualización de trayectos y paradas** por consola mediante un menú interactivo.
- **Exportación de la última posición conocida** de cada bus en formato JSON.
- **Filtrado de registros** por autobús y rango horario.
- **Simulación de cambios en la ruta** modificando coordenadas GPS.
- **Archivado automático de archivos CSV antiguos**.

---

## Ciclo de Vida del Dato

### 1. **Captura o generación**
- Se generan automáticamente datos GPS cada minuto para 3 autobuses.
- Se almacenan en el archivo `datos_simulados.csv`.

### 2. **Almacenamiento**
- Los datos se cargan a memoria en una estructura `ArrayList<GPSData>`.
- Comentado en el código cómo se almacenaría en una base de datos real.

### 3. **Procesamiento**
- Validación de coordenadas y velocidades.
- Filtrado por autobús y rango horario.

### 4. **Análisis**
- Cálculo de velocidad media.
- Detección de paradas.
- Conteo de paradas por ruta (ida / vuelta).

### 5. **Distribución o Compartición**
- Exportación de la última posición de cada bus como `bus01_status.json`, etc.

### 6. **Uso**
- Interfaz de menú por consola.
- Simulación de consulta por parte del usuario.

### 7. **Mantenimiento / Actualización**
- Posibilidad de modificar registros simulando un cambio en la ruta.

### 8. **Archivado / Eliminación**
- Simulación de archivado automático de archivos CSV antiguos (más de 7 días).

---

## Tecnologías Utilizadas
- Java 21
- Gson (para exportar JSON)
- IntelliJ IDEA
- Archivos CSV y JSON

---

## Estructura del Proyecto

- `Main.java`: punto de entrada y lógica principal
- `Menu.java`: menú interactivo separado del `Main`
- `GPSData.java`: estructura para cada registro
- `GeneradorSimulacion.java`: genera datos GPS
- `CSVProcessor.java`: carga y filtra los datos
- `AnalizadorGPS.java`: analiza velocidades y paradas
- `ExportadorJSON.java`: exporta posición de buses
- `MapaParadas.java`: localiza nombre de paradas
- `RepositorioRutas.java`: rutas de ida y vuelta desde CSV
- `SimuladorTiempo.java`: gestiona el tiempo simulado
- `Archivador.java`: mueve archivos CSV antiguos a `/archivados`

---

## Cómo Ejecutar el Proyecto

1. Abrir el proyecto en IntelliJ IDEA.
2. Asegurarse de tener la librería `gson-2.10.1.jar` añadida al classpath.
3. Ejecutar la clase `Main.java`.
4. Seguir las instrucciones del menú por consola.

---

## Autor
Elena Mesa — Proyecto de Digitalización DAM

---

## 📎 Enlace al Repositorio
[Enlace a GitHub] (https://github.com/KameHelen/gestion-autobuses-la-rinconada)

---
