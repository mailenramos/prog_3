package TPE;
public class Camion {
    private String id;
    private String patente;
    private boolean esRefrigerado;
    private int capacidadMax;

    public Camion(String id, String patente, boolean esRefrigerado, int capacidadMax) {
        this.id = id;
        this.patente = patente;
        this.esRefrigerado = esRefrigerado;
        this.capacidadMax = capacidadMax;
    }

    public String getId() { return id; }
    public String getPatente() { return patente; }
    public boolean esRefrigerado() { return esRefrigerado; }
    public int getCapacidadMax() { return capacidadMax; }
    
    @Override
    public String toString() {
        return "Camión [ID: " + id + 
            " | Patente: " + patente + 
            " | Refrigerado: " + (esRefrigerado ? "Sí" : "No") + 
            " | Capacidad Máx: " + capacidadMax + " kg]";
    }
}