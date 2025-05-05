import java.util.*;

public class MapaParadas {

    // Mapa de paradas: clave = nombre, valor = [lat, lon]
    private static final Map<String, double[]> paradas = new HashMap<>();

    static {
        //Paradas IDA
        paradas.put("Rancho – Ctra. Nueva", new double[]{37.48964226535726, -5.9782882482309825});
        paradas.put("Consultorio – C/ Gines", new double[]{37.48874069115215, -5.981083464259706});
        paradas.put("C/ Naranjo – Estacada", new double[]{37.4902191391817, -5.984622968879825});
        paradas.put("C/ Saeta – Hogar Pensionista", new double[]{37.48666717361911, -5.984833766858885});
        paradas.put("Parque Quintero León y Quiroga – Frente teatro", new double[]{37.484788634942745, -5.982334401322923});
        paradas.put("C/ Goya – C.E.I.P. Guadalquivir", new double[]{37.48419781210832, -5.9798042711648955});
        paradas.put("Avda. Unión – Nuevas viviendas / Biblioteca (IDA)", new double[]{37.48578177853421, -5.977522329725711});
        paradas.put("Avda. Unión – Nueva Jefatura (IDA)", new double[]{37.48154846186338, -5.953982847278766});
        paradas.put("Plaza de los Inventores – C/ Los Carteros", new double[]{37.4793215703277, -5.950182127993438});
        paradas.put("Rotonda Azucareros", new double[]{37.47972209350088, -5.94521438960478});
        paradas.put("Estación RENFE", new double[]{37.48020604107975, -5.940294900081402});
        paradas.put("C/ San José", new double[]{37.482028304280526, -5.944088769439743});
        paradas.put("C/ Alberto Lista – Área Mujer", new double[]{37.48278645822794, -5.946792971264663});
        paradas.put("C/ Alberto Lista – Farmacia", new double[]{37.48497200394839, -5.947373404657382});
        paradas.put("C/ Juan de la Cueva – Frente 8 de Marzo", new double[]{37.4869411504422, -5.948416437189104});
        paradas.put("C/ Fernando el Santo, 37", new double[]{37.487143630433835, -5.946145369403259});
        paradas.put("C/ Madrid – PUA", new double[]{37.488157330132616, -5.943277290681927});
        paradas.put("Boyeros – Parque Valle Inclán", new double[]{37.49056453783422, -5.945545786548968});
        paradas.put("Avda. Jardín de las Delicias – Ambul. El Mirador", new double[]{37.49235069484786, -5.941455798622887});
        paradas.put("Avda. Jardín de las Delicias – Estadio", new double[]{37.49115805242672, -5.938581954480537});
        paradas.put("Vereda de Chapatales – Teatro de la Villa", new double[]{37.48795776838909, -5.937234821202402});
        paradas.put("Rotonda Parque Dehesa Boyal – P.I. El Cáñamo I", new double[]{37.48978682875943, -5.934583548580631});
        paradas.put("Nueva Estación", new double[]{37.486345007982266, -5.933842130196105});

        // Paradas VUELTA
        paradas.put("Ctra. Bética – Muebles Madrid", new double[]{37.484726027876945, -5.938668786137706});
        paradas.put("Ctra. Bética – Estanco", new double[]{37.48388953347738, -5.941147675568416});
        paradas.put("C/ San José Vuelta – Puerta Carlos Moto", new double[]{37.482047912430666, -5.944080836529612});
        paradas.put("Estación RENFE Vuelta", new double[]{37.480549556116316, -5.940123252050491});
        paradas.put("C/ Los Carteros – Pisos Azucarera", new double[]{37.479934311378315, -5.946528152477125});
        paradas.put("C/ Los Carteros – Pabellón", new double[]{37.47945510685859, -5.9500366273254395});
        paradas.put("Avda. de la Unión – Nueva Jefatura (Vuelta)", new double[]{37.48192307644016, -5.9538807768227695});
        paradas.put("Avda. de la Unión – Nuevas Viviendas / Biblioteca (Vuelta)", new double[]{37.486094877448686, -5.9773163118009185});
        paradas.put("Francisco García – Tendido 12", new double[]{37.48847907149281, -5.9776926966886865});
    }


    // Buscar el nombre de la parada más cercana a unas coordenadas
    public static String obtenerNombreParada(double lat, double lon) {
        String nombreCercano = "Parada desconocida";
        double distanciaMin = Double.MAX_VALUE;

        for (Map.Entry<String, double[]> entry : paradas.entrySet()) {
            double[] coords = entry.getValue();
            double distancia = distancia(lat, lon, coords[0], coords[1]);

            if (distancia < 0.0005) { // Umbral de cercanía en grados (~50 metros)
                if (distancia < distanciaMin) {
                    distanciaMin = distancia;
                    nombreCercano = entry.getKey();
                }
            }
        }

        return nombreCercano;
    }

    // Distancia Euclidiana simple (no es geográfica exacta pero sirve para este contexto)
    public static double distancia(double lat1, double lon1, double lat2, double lon2) {
        return Math.sqrt(Math.pow(lat1 - lat2, 2) + Math.pow(lon1 - lon2, 2));
    }

    // Obtener coordenadas de una parada por su nombre
    public static double[] getCoordenadas(String nombre) {
        return paradas.getOrDefault(nombre, null);
    }

    // Comprobar si dos coordenadas están lo suficientemente cerca
    public static boolean estaCerca(double lat1, double lon1, double lat2, double lon2) {
        return distancia(lat1, lon1, lat2, lon2) < 0.0015;


    }
    private static double redondear(double valor, int decimales) {
        double factor = Math.pow(10, decimales);
        return Math.round(valor * factor) / factor;
    }
    public static Set<String> getNombresParadas() {
        return paradas.keySet();
    }


}
