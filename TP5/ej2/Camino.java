import java.util.ArrayList;
import java.util.HashSet;

public class Camino {

    private ArrayList<Casillero> camino;
    private HashSet<Casillero> visitados;
    private int valor;

    public Camino() {
        camino = new ArrayList<>();
        visitados = new HashSet<>();
        valor = 0;
    }

    public void agregarAlCamino(Casillero c) {
        camino.add(c);
    }

    public void quitarUltimo() {
        if (!camino.isEmpty()) {
            camino.remove(camino.size() - 1);
        }
    }

    public void marcarVisitado(Casillero c) {
        visitados.add(c);
    }

    public void quitarVisitado(Casillero c) {
        visitados.remove(c);
    }

    public boolean estaVisitado(Casillero c) {
        return visitados.contains(c);
    }

    public void incrementar(int valor) {
        this.valor += valor;
    }

    public void decrementar(int valor) {
        this.valor -= valor;
    }

    public int getValor() {
        return valor;
    }

    public ArrayList<Casillero> getCamino() {
        return camino;
    }

    @Override
    public String toString() {
        return "Costo = " + valor + " | Largo = " + camino.size();
    }
}