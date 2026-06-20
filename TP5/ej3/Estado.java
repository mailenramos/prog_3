package Backtracking.Ej3;

import java.util.ArrayList;

public class Estado {
    private ArrayList<Integer> conjunto;
    private ArrayList<Integer> solucion;

    public Estado(ArrayList<Integer> conjunto) {
        this.conjunto = conjunto;
        this.solucion = new ArrayList<>();
    }
    public ArrayList<Integer> getSolucion(){
        return new ArrayList<>(solucion);
    }

    public ArrayList<Integer> getConjunto(){
        return new ArrayList<>(conjunto);
    }

    public Integer getSuma(){
        Integer suma = 0;
        for (Integer val: solucion) {
            suma += val;
        }
        return suma;
    }

    public Integer sacarPrimeroDelConjunto(){
        return conjunto.remove(0);
    }

    public void colocarPrimeroAlConjunto(Integer primero){
        conjunto.add(0,primero);
    }

    public void addSolucion(Integer elemento){
        this.solucion.add(elemento);
    }

    public void removeSolucion(Integer elemento){
        this.solucion.remove(elemento);
    }

}
