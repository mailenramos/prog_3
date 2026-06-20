package Backtracking.Ej3;

import java.util.ArrayList;

/**
 * Suma de subconjuntos. Dados n números positivos distintos, se desea encontrar todas las
 * combinaciones de esos números tal que la suma sea igual a M.
 */
public class SumaSubconjuntos {

    private ArrayList<Integer> conjunto;
    private Integer m;
    ArrayList<ArrayList<Integer>> solucion;

    public SumaSubconjuntos(ArrayList<Integer> conjunto, Integer m) {
        this.conjunto = conjunto;
        this.m = m;
    }

    public void getSumaSubconjuntos(){
        Estado e = new Estado(this.conjunto);
        solucion = new ArrayList<>();
        backtracking(e);
        System.out.println("Solucion: " + this.solucion);
    }

    public void backtracking(Estado estado){
        //System.out.println("Conjunto Actual: " + estado.getConjunto());
        //System.out.println("Solucion Actual: " + estado.getSolucion());
        if (estado.getConjunto().isEmpty()){
            if (estado.getSuma().equals(this.m)){
                if (!this.solucion.contains(estado.getSolucion())){
                    this.solucion.add(new ArrayList<>(estado.getSolucion()));
                }
            }
        }
        else {
            Integer val = estado.sacarPrimeroDelConjunto();

            estado.addSolucion(val);
            backtracking(estado);
            // backtrack
            estado.removeSolucion(val);
            if (estado.getSuma() <= this.m){
                backtracking(estado);
            }
            estado.colocarPrimeroAlConjunto(val);
        }
    }
}