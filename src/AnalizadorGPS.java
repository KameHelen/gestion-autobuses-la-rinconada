import java.util.ArrayList;
import java.util.List;


public class AnalizadorGPS {


    //calcula la velocidad media ignorando la velocidad 0 (paradas)
    public static double calcularVelocidadMedia(List<GPSData> datos){
        double suma = 0;
        int contador = 0;

        for(GPSData d : datos){
            if (d.getSpeed()>0){ //filtrar las paradas
                suma += d.getSpeed();
                contador++;
            }
        }
        if (contador == 0) return 0;
        return suma/contador;

    }

    public static List<GPSData> detectarParadas(List<GPSData> datos) {
        List<GPSData> paradas = new ArrayList<>();

        for (GPSData d : datos) {
            if (d.getSpeed() == 0) {
                paradas.add(d);
            }
        }

        return paradas;
    }



}
