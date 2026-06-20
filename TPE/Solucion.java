package TPE;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solucion {
    private Map<Camion, List<Paquete>> asignacion;
    private int pesoNoAsignado;
    private int metricaCosto; // "estados generados" o "candidatos considerados"

    public Solucion() {
        this.asignacion = new HashMap<>();
        this.pesoNoAsignado = 0;
        this.metricaCosto = 0;
    }

    public void asignarPaquete(Camion camion, Paquete paquete) {
        if (!asignacion.containsKey(camion)) {
            asignacion.put(camion, new ArrayList<>());
        }
        asignacion.get(camion).add(paquete);
    }

    // Getters y Setters
    public Map<Camion, List<Paquete>> getAsignacion() {
        return asignacion;
    }

    public int getPesoNoAsignado() {
        return pesoNoAsignado;
    }

    public void setPesoNoAsignado(int pesoNoAsignado) {
        this.pesoNoAsignado = pesoNoAsignado;
    }

    public int getMetricaCosto() {
        return metricaCosto;
    }

    public void setMetricaCosto(int metricaCosto) {
        this.metricaCosto = metricaCosto;
    }

    public void mostrarResultado(String tecnica) {

        for (Map.Entry<Camion, List<Paquete>> entrada : asignacion.entrySet()) {
            System.out.println("  " + entrada.getKey());
            for (Paquete p : entrada.getValue()) {
                System.out.println("    -> " + p);
            }
        }

        System.out.println("Peso no asignado: " + pesoNoAsignado + " kg.");
        if (tecnica.equalsIgnoreCase("Backtracking")) {
            System.out.println("Métrica para analizar el costo (cantidad de estados generados): " + metricaCosto);
        } else {
            System.out.println("Métrica para analizar el costo (cantidad de candidatos considerados): " + metricaCosto);
        }
        System.out.println("--------------------------------------------------\n");
    }
}