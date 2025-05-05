
import java.time.LocalDateTime;

// En un entorno real, estos datos se almacenarían en una base de datos relacional como MySQL o PostgreSQL:
// Tabla: gps_data
// Columnas: id, bus_id, latitude, longitude, timestamp, speed

// También se podrían usar bases NoSQL como MongoDB, que permiten guardar documentos JSON sin esquema fijo.


public class GPSData {

    private String busId;
    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;
    private double speed; // en km/h

    public GPSData(String busId, double latitude, double longitude, LocalDateTime timestamp, double speed) {
        this.busId = busId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.speed = speed;
    }

    public String getBusId() {
        return busId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getSpeed() {
        return speed;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        return "GPSData{" +
                "busId='" + busId + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                ", speed=" + speed +
                '}';
    }
}
