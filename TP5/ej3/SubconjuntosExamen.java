package Backtracking.Ej3;

import java.util.ArrayList;
import java.util.List;

/**
 * Suma de subconjuntos. Dados n números positivos distintos, se desea encontrar todas las
 * combinaciones de esos números tal que la suma sea igual a M.
 */

public class SubconjuntosExamen {
    private ArrayList<Integer> subconjunto = new ArrayList<>(); // {1 6 5 7 3 2} // 1 2   3

    public List<Integer> getSumaSubconjuntos(Integer m) {
        List<Integer> conjuntoActual = new ArrayList<>();
        List<Integer> conjuntoSolucion = new ArrayList<>();

        getSumaSubconjuntos(conjuntoActual, conjuntoSolucion, 3, 0);

        return conjuntoSolucion;
    }

    private void getSumaSubconjuntos(List<Integer> conjuntoActual, List<Integer> conjuntoSolucion, Integer m, Integer suma) {

        if (subconjunto.isEmpty()) {
            if (suma.equals(m)) {
                conjuntoSolucion.addAll(conjuntoActual);
            }
        } else {
            Integer numero = subconjunto.remove(0);
            getSumaSubconjuntos(conjuntoActual, conjuntoSolucion, m, suma);

            suma += numero;
            conjuntoActual.add(numero);
            getSumaSubconjuntos(conjuntoActual, conjuntoSolucion, m, suma);
            suma -= numero;
            conjuntoActual.remove(conjuntoActual.size() - 1);
        }
    }
}