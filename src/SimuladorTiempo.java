import java.time.LocalDateTime;

public class SimuladorTiempo {
    private static LocalDateTime ahoraSimulado;

    // Inicializa con la última hora de los datos
    public static void inicializar(LocalDateTime referencia) {
        ahoraSimulado = referencia;
    }

    // Obtener la hora simulada actual
    public static LocalDateTime ahora() {
        return ahoraSimulado;
    }

    // Avanzar o retroceder tiempo si quieres más tarde
    public static void avanzarMinutos(long minutos) {
        ahoraSimulado = ahoraSimulado.plusMinutes(minutos);
    }

    public static void retrocederMinutos(long minutos) {
        ahoraSimulado = ahoraSimulado.minusMinutes(minutos);
    }
}
