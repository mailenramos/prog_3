package PARCIAL_2026;

import java.util.ArrayList;

public class Greedy {
/*Se tienen n objetos y una mochila. Para i = 1,2,..n, el objeto i tiene un peso positivo pi y un valor positivo vi. La
mochila puede llevar un peso que no sobrepase P. El objetivo es llenar la mochila de tal manera que se maximice el
valor de los objetos transportados, respetando la limitación de capacidad impuesta. Los objetos pueden ser
fraccionados, si una fracción xi (0 ≤ xi ≤ 1) del objeto i es ubicada en la mochila contribuye en xi*pi al peso total de la
mochila y en xi*vi al valor de la carga. Se le pide resolver el problema mediante un algoritmo greedy que siempre
encuentre la solución óptima.*/

    public ArrayList<Double> greedyMochilaFraccionaria( ArrayList<Double>pesos,int capacidadMochila,ArrayList<Integer> valores){
        ArrayList<Double> resultado=new ArrayList<>(pesos.size());

        for(int i=0 ;i<=resultado.size();i++){
            resultado.add(i,0.0);//inicializa todas las fracciones en 0.0
        }

        double pesoActual = 0;

        while(pesoActual < capacidadMochila){
            int candidato = seleccion(pesos,valores, resultado);

            if (candidato == -1) {
                break; 
            }
            // Si el objeto entra entero en lo que queda de espacio
            if( (pesoActual + pesos.get(candidato) <= capacidadMochila)){
                resultado.set(candidato, 1.0);
                pesoActual += pesos.get(candidato);
            // Si no entra entero, tomamos solo la fracción que quepa
            }else{
                double espacioDisponible=capacidadMochila - pesoActual;
                double fraccion=espacioDisponible/pesos.get(candidato);
                resultado.set(candidato ,fraccion);
                pesoActual = capacidadMochila;
            }
        }
        return resultado;
    }

    public int seleccion(ArrayList<Double> pesos, ArrayList<Integer> valores, ArrayList<Double> resultado) {
        int mejorIndice = -1;
        double maxRelacion = -1.0;

        for (int i = 0; i < pesos.size(); i++) {
            if (resultado.get(i) == 0.0) {// Solo consideramos objetos que no hayamos metido ya en la mochila (resultado == 0.0)
                
                double relacionActual = (double) valores.get(i) / pesos.get(i);// Calculamos la relación valor/peso (Greedy óptimo)

                if (relacionActual > maxRelacion) {
                    maxRelacion = relacionActual;
                    mejorIndice = i;
                }
            }
        }
        
        return mejorIndice; // Retorna el índice del mejor objeto disponible
    }
}
