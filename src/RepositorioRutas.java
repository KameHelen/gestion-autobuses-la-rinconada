public class RepositorioRutas {

    private static Ruta rutaIda;
    private static Ruta rutaVuelta;

    static {
        // Inicializar rutas al cargar la clase
        inicializarRutas();
    }

    private static void inicializarRutas() {
        rutaIda = new Ruta("Línea 1 - Ida");
        rutaVuelta = new Ruta("Línea 1 - Vuelta");


        // Paradas de IDA
        rutaIda.agregarParada(new Parada("Rancho – Ctra. Nueva", 37.48964226535726, -5.9782882482309825));
        rutaIda.agregarParada(new Parada("Consultorio – C/ Gines", 37.48874069115215, -5.981083464259706));
        rutaIda.agregarParada(new Parada("C/ Naranjo – Estacada", 37.4902191391817, -5.984622968879825));
        rutaIda.agregarParada(new Parada("C/ Saeta – Hogar Pensionista", 37.48666717361911, -5.984833766858885));
        rutaIda.agregarParada(new Parada("Parque Quintero León y Quiroga – Frente teatro", 37.484788634942745, -5.982334401322923));
        rutaIda.agregarParada(new Parada("C/ Goya – C.E.I.P. Guadalquivir", 37.48419781210832, -5.9798042711648955));
        rutaIda.agregarParada(new Parada("Avda. Unión – Nuevas viviendas / Biblioteca (IDA)", 37.48578177853421, -5.977522329725711));
        rutaIda.agregarParada(new Parada("Avda. Unión – Nueva Jefatura (IDA)", 37.48154846186338, -5.953982847278766));
        rutaIda.agregarParada(new Parada("Plaza de los Inventores – C/ Los Carteros", 37.4793215703277, -5.950182127993438));
        rutaIda.agregarParada(new Parada("Rotonda Azucareros", 37.47972209350088, -5.94521438960478));
        rutaIda.agregarParada(new Parada("Estación RENFE", 37.48020604107975, -5.940294900081402));
        rutaIda.agregarParada(new Parada("C/ San José", 37.482028304280526, -5.944088769439743));
        rutaIda.agregarParada(new Parada("C/ Alberto Lista – Área Mujer", 37.48278645822794, -5.946792971264663));
        rutaIda.agregarParada(new Parada("C/ Alberto Lista – Farmacia", 37.48497200394839, -5.947373404657382));
        rutaIda.agregarParada(new Parada("C/ Juan de la Cueva – Frente 8 de Marzo", 37.4869411504422, -5.948416437189104));
        rutaIda.agregarParada(new Parada("C/ Fernando el Santo, 37", 37.487143630433835, -5.946145369403259));
        rutaIda.agregarParada(new Parada("C/ Madrid – PUA", 37.488157330132616, -5.943277290681927));
        rutaIda.agregarParada(new Parada("Boyeros – Parque Valle Inclán", 37.49056453783422, -5.945545786548968));
        rutaIda.agregarParada(new Parada("Avda. Jardín de las Delicias – Ambul. El Mirador", 37.49235069484786, -5.941455798622887));
        rutaIda.agregarParada(new Parada("Avda. Jardín de las Delicias – Estadio", 37.49115805242672, -5.938581954480537));
        rutaIda.agregarParada(new Parada("Vereda de Chapatales – Teatro de la Villa", 37.48795776838909, -5.937234821202402));
        rutaIda.agregarParada(new Parada("Rotonda Parque Dehesa Boyal – P.I. El Cáñamo I", 37.48978682875943, -5.934583548580631));
        rutaIda.agregarParada(new Parada("Nueva Estación", 37.486345007982266, -5.933842130196105));

        // Paradas de VUELTA
        rutaVuelta.agregarParada(new Parada("Ctra. Bética – Muebles Madrid", 37.484726027876945, -5.938668786137706));
        rutaVuelta.agregarParada(new Parada("Ctra. Bética – Estanco", 37.48388953347738, -5.941147675568416));
        rutaVuelta.agregarParada(new Parada("C/ San José Vuelta – Puerta Carlos Moto", 37.482047912430666, -5.944080836529612));
        rutaVuelta.agregarParada(new Parada("Estación RENFE Vuelta", 37.480549556116316, -5.940123252050491));
        rutaVuelta.agregarParada(new Parada("C/ Los Carteros – Pisos Azucarera", 37.479934311378315, -5.946528152477125));
        rutaVuelta.agregarParada(new Parada("C/ Los Carteros – Pabellón", 37.47945510685859, -5.9500366273254395));
        rutaVuelta.agregarParada(new Parada("Avda. de la Unión – Nueva Jefatura (Vuelta)", 37.48192307644016, -5.9538807768227695));
        rutaVuelta.agregarParada(new Parada("Avda. de la Unión – Nuevas Viviendas / Biblioteca (Vuelta)", 37.486094877448686, -5.9773163118009185));
        rutaVuelta.agregarParada(new Parada("Francisco García – Tendido 12", 37.48847907149281, -5.9776926966886865));
    }

    public static Ruta getRutaIda() {
        return rutaIda;
    }

    public static Ruta getRutaVuelta() {
        return rutaVuelta;
    }
}
