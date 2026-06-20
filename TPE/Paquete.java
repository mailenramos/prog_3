package TPE;
public class Paquete {
    private String id;
    private String codId;
    private int pesoKg;
    private boolean contieneAlimentos;
    private  int nivelUrgencia;

    public Paquete(String id, String codId, int pesoKg, boolean contieneAlimentos, int nivelUrgencia) {
        this.id = id;
        this.codId = codId;
        this.pesoKg = pesoKg;
        this.contieneAlimentos = contieneAlimentos;
        if(nivelUrgencia > 0 && nivelUrgencia <= 100 )
            this.nivelUrgencia = nivelUrgencia;
    }

    public String getId() { return id; }
    public String getCodId() { return codId; }
    public int getPeso() { return pesoKg; }
    public boolean contieneAlimentos() { return contieneAlimentos; }
    public int  getNivelUrgencia(){ return nivelUrgencia; }
    @Override
    public String toString() {
        return "Paquete [Código: " + codId + 
            " | Urgencia: " + nivelUrgencia + 
            " | Alimentos: " + (contieneAlimentos ? "Sí" : "No") + "]";
    }
}