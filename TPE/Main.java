package TPE;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Definimos las rutas a los archivos dentro de la carpeta TPE
        String pathCamiones = "TPE/Camiones.csv";
        String pathPaquetes = "TPE/Paquetes.csv";

        // Instanciamos la clase Servicios
        Servicios sistema = new Servicios(pathCamiones, pathPaquetes);
        
        System.out.println("--------------------------------------------------");
        System.out.println("--- PRUEBA SERVICIO 1: Buscar por Código ---");
        
        String codigoABuscar = "P002";
        System.out.println("Buscando el paquete con código: " + codigoABuscar);
        
        Paquete paqueteEncontrado = sistema.servicio1(codigoABuscar);
        
        if (paqueteEncontrado != null) {
            System.out.println("¡Paquete encontrado!");
            System.out.println(paqueteEncontrado); 
        } else {
            System.out.println("No se encontró ningún paquete con el código: " + codigoABuscar);
        }
        
        System.out.println("--------------------------------------------------");
        System.out.println("--- PRUEBA SERVICIO 2: Filtrar por Alimentos ---");
        boolean buscarConAlimentos = true; 
        
        System.out.println("Filtrando paquetes que " + (buscarConAlimentos ? "SÍ" : "NO") + " contienen alimentos:");
        List<Paquete> paquetesPorAlimento = sistema.servicio2(buscarConAlimentos);
        
        System.out.println("Cantidad de paquetes encontrados: " + paquetesPorAlimento.size());
        for (Paquete p : paquetesPorAlimento) {
            System.out.println(p); 
        }
        
        System.out.println("--------------------------------------------------");
        System.out.println("--- PRUEBA SERVICIO 3: Rango de Urgencia ---");
        int minUrgencia = 2;
        int maxUrgencia = 4;
        
        System.out.println("Filtrando paquetes con urgencia entre " + minUrgencia + " y " + maxUrgencia + " (inclusive):");
        List<Paquete> paquetesPorUrgencia = sistema.servicio3(minUrgencia, maxUrgencia);
        
        System.out.println("Cantidad de paquetes encontrados: " + paquetesPorUrgencia.size());
        for (Paquete p : paquetesPorUrgencia) {
            System.out.println(p); 
        }
        
        System.out.println("--------------------------------------------------");
        System.out.println("--- GREEDY ---");
        System.out.println("--------------------------------------------------");

        Solucion S = sistema.greedy();
        S.mostrarResultado("Greedy");


        System.out.println("--------------------------------------------------");
        System.out.println("--- BACKTRACKING ---");
        System.out.println("--------------------------------------------------");

        Solucion solBacktracking = sistema.backtracking();
        solBacktracking.mostrarResultado("Backtracking");
    }
}   