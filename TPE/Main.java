package TPE;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Definimos las rutas a los archivos dentro de la carpeta TPE
        String pathCamiones = "TPE/Camiones.csv";
        String pathPaquetes = "TPE/Paquetes.csv";

        System.out.println("=== INICIANDO SISTEMA DE SERVICIOS ===");
        System.out.println("Cargando datos desde la carpeta TPE...");

        // 2. Instanciamos la clase Servicios
        Servicios sistema = new Servicios(pathCamiones, pathPaquetes);
        
        System.out.println("¡Datos cargados con éxito!\n");
        System.out.println("--------------------------------------------------");

        // ==========================================
        // PRUEBA DE SERVICIO 1: Buscar paquete por código
        // ==========================================
        System.out.println("=== PRUEBA SERVICIO 1: Buscar por Código ===");
        
        String codigoABuscar = "P002"; // Cambialo por un código real de tu CSV
        System.out.println("Buscando el paquete con código: " + codigoABuscar);
        
        Paquete paqueteEncontrado = sistema.servicio1(codigoABuscar);
        
        if (paqueteEncontrado != null) {
            System.out.println("¡Paquete encontrado!");
            // Al pasarle el objeto directamente a println, Java usa el toString() automáticamente
            System.out.println(paqueteEncontrado); 
        } else {
            System.out.println("No se encontró ningún paquete con el código: " + codigoABuscar);
        }
        
        System.out.println("--------------------------------------------------");

        // ==========================================
        // PRUEBA DE SERVICIO 2: Filtrar por Alimentos
        // ==========================================
        System.out.println("=== PRUEBA SERVICIO 2: Filtrar por Alimentos ===");
        boolean buscarConAlimentos = true; 
        
        System.out.println("Filtrando paquetes que " + (buscarConAlimentos ? "SÍ" : "NO") + " contienen alimentos:");
        List<Paquete> paquetesPorAlimento = sistema.servicio2(buscarConAlimentos);
        
        System.out.println("Cantidad de paquetes encontrados: " + paquetesPorAlimento.size());
        for (Paquete p : paquetesPorAlimento) {
            System.out.println(p); // Imprime el paquete usando su toString()
        }
        
        System.out.println("--------------------------------------------------");

        // ==========================================
        // PRUEBA DE SERVICIO 3: Filtrar por Rango de Urgencia
        // ==========================================
        System.out.println("=== PRUEBA SERVICIO 3: Rango de Urgencia ===");
        int minUrgencia = 2;
        int maxUrgencia = 4;
        
        System.out.println("Filtrando paquetes con urgencia entre " + minUrgencia + " y " + maxUrgencia + " (inclusive):");
        List<Paquete> paquetesPorUrgencia = sistema.servicio3(minUrgencia, maxUrgencia);
        
        System.out.println("Cantidad de paquetes encontrados: " + paquetesPorUrgencia.size());
        for (Paquete p : paquetesPorUrgencia) {
            System.out.println(p); // Imprime el paquete usando su toString()
        }

        System.out.println("--------------------------------------------------");
        System.out.println("=== FIN DE LAS PRUEBAS ===");
    }
}