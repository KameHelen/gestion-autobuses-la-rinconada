import java.util.ArrayList;
import java.util.List;

public class Ruta {
    private String nombre;
    private List<Parada> paradas;

    public Ruta(String nombre) {
        this.nombre = nombre;
        this.paradas = new ArrayList<>();
    }

    public void agregarParada(Parada p) {
        paradas.add(p);
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public String getNombre() {
        return nombre;
    }

}
